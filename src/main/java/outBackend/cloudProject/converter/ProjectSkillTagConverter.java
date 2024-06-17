package outBackend.cloudProject.converter;

import outBackend.cloudProject.domain.SkillTag;
import outBackend.cloudProject.domain.mapping.ProjectSkillTag;

import java.util.List;
import java.util.stream.Collectors;

public class ProjectSkillTagConverter {
    public static List<ProjectSkillTag> toProjectSkillTagList(List<SkillTag> skillTagList) {
        return skillTagList.stream()
                .map(skillTag -> ProjectSkillTag.builder()
                        .skillTag(skillTag)
                        .build()).collect(Collectors.toList());
    }
}
