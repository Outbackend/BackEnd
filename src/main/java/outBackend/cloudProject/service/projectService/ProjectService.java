package outBackend.cloudProject.service.projectService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import outBackend.cloudProject.apiPayload.code.status.ErrorStatus;
import outBackend.cloudProject.apiPayload.exception.handler.PositionHandler;
import outBackend.cloudProject.apiPayload.exception.handler.ProjectHandler;
import outBackend.cloudProject.apiPayload.exception.handler.SkillTagHandler;
import outBackend.cloudProject.converter.ProjectConverter;
import outBackend.cloudProject.converter.ProjectPositionConverter;
import outBackend.cloudProject.converter.ProjectSkillTagConverter;
import outBackend.cloudProject.domain.Member;
import outBackend.cloudProject.domain.Position;
import outBackend.cloudProject.domain.Project;
import outBackend.cloudProject.domain.SkillTag;
import outBackend.cloudProject.domain.enums.ProjectStatus;
import outBackend.cloudProject.domain.mapping.MemberProject;
import outBackend.cloudProject.domain.mapping.ProjectPosition;
import outBackend.cloudProject.domain.mapping.ProjectSkillTag;
import outBackend.cloudProject.dto.ProjectRequestDTO;
import outBackend.cloudProject.repository.*;
import outBackend.cloudProject.security.TokenProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final SkillTagRepository skillTagRepository;
    private final PositionRepository positionRepository;
    private final ProjectSkillTagRepository projectSkillTagRepository;
    private final ProjectPositionRepository projectPositionRepository;
    private final MemberRepository memberRepository;
    private final MemberProjectRepository memberProjectRepository;

    private final TokenProvider tokenProvider;


    @Transactional
    public Project projectInfo(Long projectId) {
        Project project = projectRepository.findById(projectId).orElse(null);
        return project;
    }

    @Transactional
    public Project save(String accessToken, ProjectRequestDTO.SaveDTO saveRequest){

        //  토큰 정보 기반으로 member 정보 가져오기
        Authentication authentication = tokenProvider.getAuthentication(accessToken);
        Member member = memberRepository.findByEmail(authentication.getName()).get();

        // 프로젝트 생성 request dto와 member 정보 이용해서 project 객체 생성
        Project project = ProjectConverter.toproject(member, saveRequest);

        List<SkillTag> skillTagList = saveRequest.getSkillTagList().stream()
                .map(skillTag -> {
                    return skillTagRepository.findByName(skillTag).orElseThrow(() -> new SkillTagHandler(ErrorStatus._SKILLTAG_NOT_FOUND));
                }).collect(Collectors.toList());

        List<ProjectSkillTag> projectSkillTagList = ProjectSkillTagConverter.toProjectSkillTagList(skillTagList);

        //  projectSkillTag, project, skillTag 연관 관계 맺기
        for(ProjectSkillTag projectSkillTag : projectSkillTagList) {
            projectSkillTag.setProject(project);
            projectSkillTag.setSkillTag(projectSkillTag.getSkillTag());
            projectSkillTagRepository.save(projectSkillTag);
        }

        List<Position> positionList = saveRequest.getPositionList().stream()
                .map(position -> {
                    return positionRepository.findByName(position).orElseThrow(() -> new PositionHandler(ErrorStatus._POSITION_NOT_FOUND));
                }).collect(Collectors.toList());

        List<ProjectPosition> projectPositionList = ProjectPositionConverter.toProjectPositionList(positionList);

        //  projectPosition, project, position 연관 관계 맺기
        for(ProjectPosition projectPosition : projectPositionList) {
            projectPosition.setProject(project);
            projectPosition.setPosition(projectPosition.getPosition());
            projectPositionRepository.save(projectPosition);
        }

        //  project, member(creator), memberProject 연관 관계 맺기
        MemberProject memberProject = MemberProject.builder().build();
        memberProject.setMember(member);
        memberProject.setProject(project);
        memberProjectRepository.save(memberProject);

        return projectRepository.save(project);
    }

    @Transactional
    public MemberProject addMember(String accessToken, ProjectRequestDTO.addMemberToProjectDTO request){

        //  토큰 정보 기반으로 member 정보 가져오기
        Authentication authentication = tokenProvider.getAuthentication(accessToken);
        Member member = memberRepository.findByEmail(authentication.getName()).get();

        Project project = projectRepository.findById(request.getProjectId()).get();

        //  프로젝트의 creator가 아닌 사람이 회원 추가 요청을 하면 에러 코드 발생
        if(!(member.getId().equals(project.getCreaterId()))){
            throw new ProjectHandler(ErrorStatus._FORBIDDEN);
        }

        Member newMember = memberRepository.findById(request.getMemberId()).get();

        MemberProject memberProject = MemberProject.builder().build();
        memberProject.setMember(newMember);
        memberProject.setProject(project);
        memberProjectRepository.save(memberProject);

        return memberProject;
    }

    @Transactional
    public void deleteProject(String accessToken, Long projectId){

        //  토큰 정보 기반으로 member 정보 가져오기
        Authentication authentication = tokenProvider.getAuthentication(accessToken);
        Member member = memberRepository.findByEmail(authentication.getName()).get();

        Project project = projectRepository.findById(projectId).get();

        //  프로젝트의 creator가 아닌 사람이 프로젝트 삭제 요청을 하면 에러 코드 발생
        if(!(member.getId().equals(project.getCreaterId()))){
            throw new ProjectHandler(ErrorStatus._FORBIDDEN);
        }

        //  -----------

        List<MemberProject> memberRemoveList = new ArrayList<>();

        for(MemberProject memberProject : project.getMemberProjectList()){
            memberRemoveList.add(memberProject);
            memberProjectRepository.delete(memberProject);
        }
        project.getMemberProjectList().removeAll(memberRemoveList);

        //  -----------

        List<ProjectPosition> positionRemoveList = new ArrayList<>();

        for(ProjectPosition projectPosition : project.getProjectPositionList()){
            positionRemoveList.add(projectPosition);
            projectPositionRepository.delete(projectPosition);
        }
        project.getProjectPositionList().removeAll(positionRemoveList);

        //  -----------

        List<ProjectSkillTag> skillTagRemoveList = new ArrayList<>();

        for(ProjectSkillTag projectSkillTag : project.getProjectSkillTagList()){
            skillTagRemoveList.add(projectSkillTag);
            projectSkillTagRepository.delete(projectSkillTag);
        }
        project.getProjectSkillTagList().removeAll(skillTagRemoveList);

        //  -----------

        projectRepository.delete(project);
    }

    @Transactional
    public Project updateProject(String accessToken, Long project_id, ProjectRequestDTO.updateProjectDTO updateRequest){

        //  토큰 정보 기반으로 member 정보 가져오기
        Authentication authentication = tokenProvider.getAuthentication(accessToken);
        Member member = memberRepository.findByEmail(authentication.getName()).get();

        Project project = projectRepository.findById(project_id).get();

        //  프로젝트의 creator가 아닌 사람이 프로젝트 변경 요청을 하면 에러 코드 발생
        if(!(member.getId().equals(project.getCreaterId()))){
            throw new ProjectHandler(ErrorStatus._FORBIDDEN);
        }

        if(updateRequest.getTitle().isPresent()){
            project.setTitle(updateRequest.getTitle().get());
        }

        if(updateRequest.getContent().isPresent()){
            project.setContent(updateRequest.getContent().get());
        }

        if(updateRequest.getDeadline().isPresent()){
            project.setDeadline(updateRequest.getDeadline().get());
        }

        if(updateRequest.getProjectStatus().isPresent()){
            switch (updateRequest.getProjectStatus().get()){
                case "RECRUITING":
                    project.setProjectStatus(ProjectStatus.RECRUITING);
                    break;
                case "IN_PROGRESS":
                    project.setProjectStatus(ProjectStatus.IN_PROGRESS);
                    break;
                case "COMPLETED":
                    project.setProjectStatus(ProjectStatus.COMPLETED);
                    break;
            }
        }

        //  -----------

        List<ProjectPosition> positionRemoveList = new ArrayList<>();

        for(ProjectPosition projectPosition : project.getProjectPositionList()){
            positionRemoveList.add(projectPosition);
            projectPositionRepository.delete(projectPosition);
        }
        project.getProjectPositionList().removeAll(positionRemoveList);

        //  -----------

        List<ProjectSkillTag> skillTagRemoveList = new ArrayList<>();

        for(ProjectSkillTag projectSkillTag : project.getProjectSkillTagList()){
            skillTagRemoveList.add(projectSkillTag);
            projectSkillTagRepository.delete(projectSkillTag);
        }
        project.getProjectSkillTagList().removeAll(skillTagRemoveList);

        //  -----------

        List<SkillTag> skillTagList = updateRequest.getSkillTagList().stream()
                .map(skillTag -> {
                    return skillTagRepository.findByName(skillTag).orElseThrow(() -> new SkillTagHandler(ErrorStatus._SKILLTAG_NOT_FOUND));
                }).collect(Collectors.toList());

        List<ProjectSkillTag> projectSkillTagList = ProjectSkillTagConverter.toProjectSkillTagList(skillTagList);

        //  projectSkillTag, project, skillTag 연관 관계 맺기
        for(ProjectSkillTag projectSkillTag : projectSkillTagList) {
            projectSkillTag.setProject(project);
            projectSkillTag.setSkillTag(projectSkillTag.getSkillTag());
            projectSkillTagRepository.save(projectSkillTag);
        }

        List<Position> positionList = updateRequest.getPositionList().stream()
                .map(position -> {
                    return positionRepository.findByName(position).orElseThrow(() -> new PositionHandler(ErrorStatus._POSITION_NOT_FOUND));
                }).collect(Collectors.toList());

        List<ProjectPosition> projectPositionList = ProjectPositionConverter.toProjectPositionList(positionList);

        //  projectPosition, project, position 연관 관계 맺기
        for(ProjectPosition projectPosition : projectPositionList) {
            projectPosition.setProject(project);
            projectPosition.setPosition(projectPosition.getPosition());
            projectPositionRepository.save(projectPosition);
        }

        return project;
    }
}
