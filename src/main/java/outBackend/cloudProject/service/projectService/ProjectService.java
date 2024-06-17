package outBackend.cloudProject.service.projectService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import outBackend.cloudProject.apiPayload.code.status.ErrorStatus;
import outBackend.cloudProject.apiPayload.exception.handler.PositionHandler;
import outBackend.cloudProject.apiPayload.exception.handler.SkillTagHandler;
import outBackend.cloudProject.converter.MemberSkillTagConverter;
import outBackend.cloudProject.converter.ProjectConverter;
import outBackend.cloudProject.converter.ProjectPositionConverter;
import outBackend.cloudProject.converter.ProjectSkillTagConverter;
import outBackend.cloudProject.domain.Position;
import outBackend.cloudProject.domain.Project;
import outBackend.cloudProject.domain.SkillTag;
import outBackend.cloudProject.domain.mapping.MemberSkillTag;
import outBackend.cloudProject.domain.mapping.ProjectPosition;
import outBackend.cloudProject.domain.mapping.ProjectSkillTag;
import outBackend.cloudProject.dto.ProjectRequestDTO;
import outBackend.cloudProject.dto.ProjectResponseDTO;
import outBackend.cloudProject.repository.*;

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
    @Transactional
    public Project save(ProjectRequestDTO.SaveDTO saveRequest){

        Project project = ProjectConverter.toproject(saveRequest);

        List<SkillTag> skillTagList = saveRequest.getSkillTagList().stream()
                .map(skillTag -> {
                    return skillTagRepository.findByName(skillTag).orElseThrow(() -> new SkillTagHandler(ErrorStatus._SKILLTAG_NOT_FOUND));
                }).collect(Collectors.toList());
        List<ProjectSkillTag> projectSkillTagList = ProjectSkillTagConverter.toProjectSkillTagList(skillTagList);

        for(ProjectSkillTag projectSkillTag : projectSkillTagList) {
            projectSkillTag.setProject(project);
            projectSkillTag.setSkillTag(projectSkillTag.getSkillTag());
            projectSkillTagRepository.save(projectSkillTag);
        }
        projectRepository.save(project);

        List<Position> positionList = saveRequest.getPositionList().stream()
                .map(position -> {
                    return positionRepository.findByName(position).orElseThrow(() -> new PositionHandler(ErrorStatus._POSITION_NOT_FOUND));
                }).collect(Collectors.toList());
        List<ProjectPosition> projectPositionList = ProjectPositionConverter.toProjectPositionList(positionList);

        for(ProjectPosition projectPosition : projectPositionList) {
            projectPosition.setProject(project);
            projectPosition.setPosition(projectPosition.getPosition());
            projectPositionRepository.save(projectPosition);
        }
        return projectRepository.save(project);
    }

}
