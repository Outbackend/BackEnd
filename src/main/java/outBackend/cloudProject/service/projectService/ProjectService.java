package outBackend.cloudProject.service.projectService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import outBackend.cloudProject.apiPayload.code.status.ErrorStatus;
import outBackend.cloudProject.apiPayload.exception.handler.SkillTagHandler;
import outBackend.cloudProject.converter.MemberSkillTagConverter;
import outBackend.cloudProject.converter.ProjectConverter;
import outBackend.cloudProject.domain.Project;
import outBackend.cloudProject.domain.SkillTag;
import outBackend.cloudProject.domain.mapping.MemberSkillTag;
import outBackend.cloudProject.dto.ProjectRequestDTO;
import outBackend.cloudProject.repository.ProjectRepository;
import outBackend.cloudProject.repository.SkillTagRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final SkillTagRepository skillTagRepository;

    @Transactional
    public Project save(ProjectRequestDTO.SaveDTO saveRequest){

        Project project = ProjectConverter.toproject(saveRequest);

        List<SkillTag> skillTagList = saveRequest.getSkillTagList().stream()
                .map(skillTag -> {
                    return skillTagRepository.findByName(skillTag).orElseThrow(() -> new SkillTagHandler(ErrorStatus._SKILLTAG_NOT_FOUND));
                }).collect(Collectors.toList());
        List<MemberSkillTag> memberSkillTagList = MemberSkillTagConverter.toMemberSkillTagList(skillTagList);
        return projectRepository.save(project);
    }

}
