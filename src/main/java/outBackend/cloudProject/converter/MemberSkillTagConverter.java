package outBackend.cloudProject.converter;

import outBackend.cloudProject.domain.SkillTag;
import outBackend.cloudProject.domain.mapping.MemberSkillTag;

import java.util.List;
import java.util.stream.Collectors;

public class MemberSkillTagConverter {

    public static List<MemberSkillTag> toMemberSkillTagList(List<SkillTag> skillTagList){

        return skillTagList.stream()
                .map(skillTag -> MemberSkillTag.builder().build()).collect(Collectors.toList());
    }
}
