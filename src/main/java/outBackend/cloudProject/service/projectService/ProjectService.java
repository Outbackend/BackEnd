package outBackend.cloudProject.service.projectService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import outBackend.cloudProject.apiPayload.code.status.ErrorStatus;
import outBackend.cloudProject.apiPayload.exception.handler.PositionHandler;
import outBackend.cloudProject.apiPayload.exception.handler.ProjectHandler;
import outBackend.cloudProject.apiPayload.exception.handler.SkillTagHandler;
import outBackend.cloudProject.converter.MemberSkillTagConverter;
import outBackend.cloudProject.converter.ProjectConverter;
import outBackend.cloudProject.converter.ProjectPositionConverter;
import outBackend.cloudProject.converter.ProjectSkillTagConverter;
import outBackend.cloudProject.domain.Member;
import outBackend.cloudProject.domain.Position;
import outBackend.cloudProject.domain.Project;
import outBackend.cloudProject.domain.SkillTag;
import outBackend.cloudProject.domain.mapping.MemberProject;
import outBackend.cloudProject.domain.mapping.MemberSkillTag;
import outBackend.cloudProject.domain.mapping.ProjectPosition;
import outBackend.cloudProject.domain.mapping.ProjectSkillTag;
import outBackend.cloudProject.dto.ProjectRequestDTO;
import outBackend.cloudProject.dto.ProjectResponseDTO;
import outBackend.cloudProject.repository.*;
import outBackend.cloudProject.security.TokenProvider;

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

        System.out.println("member id = " + member.getId());
        System.out.println("creator id = " + project.getCreaterId());
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

}
