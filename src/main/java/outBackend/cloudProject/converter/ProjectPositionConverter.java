package outBackend.cloudProject.converter;

import outBackend.cloudProject.domain.Position;
import outBackend.cloudProject.domain.SkillTag;
import outBackend.cloudProject.domain.mapping.ProjectPosition;
import outBackend.cloudProject.domain.mapping.ProjectSkillTag;

import java.util.List;
import java.util.stream.Collectors;

public class ProjectPositionConverter {
    public static List<ProjectPosition> toProjectPositionList(List<Position> positionList) {
        return positionList.stream()
                .map(position -> ProjectPosition.builder()
                        .position(position)
                        .build()).collect(Collectors.toList());
    }

}
