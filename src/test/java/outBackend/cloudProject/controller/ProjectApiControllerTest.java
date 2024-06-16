package outBackend.cloudProject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import outBackend.cloudProject.domain.Project;
import outBackend.cloudProject.dto.AddProjectRequest;
import outBackend.cloudProject.dto.UpdateProjectRequest;
import outBackend.cloudProject.repository.ProjectRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProjectApiControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    ProjectRepository projectRepository;

    @BeforeEach
    public void mockMvcSetup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
        projectRepository.deleteAll();
    }

    @DisplayName("addProject: 아티클 추가 성공")
    @Test
    public void addProject() throws Exception {
        final String url = "/api/projects";
        final String title = "title";
        final String content = "content";
        final AddProjectRequest userRequest = new AddProjectRequest(title,content,
                LocalDate.of(2024,06,16));

        //객체 Json으로 직렬화
        final String requestBody = objectMapper.writeValueAsString(userRequest);

        //설정한 내용을 바탕으로 요청 전송
        ResultActions resultActions = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));

        resultActions.andExpect(status().isCreated());

        List<Project> projects = projectRepository.findAll();

        assertThat(projects.size()).isEqualTo(1);
        assertThat(projects.get(0).getTitle()).isEqualTo(title);
        assertThat(projects.get(0).getContent()).isEqualTo(content);
    }

    @DisplayName("findAllProjects: 아티클 목록 조회 성공")
    @Test
    public void findAllProjects() throws Exception {
        final String url = "/api/projects";
        final String title = "title";
        final String content = "content";

        projectRepository.save(Project.builder()
                .title(title)
                .content(content)
                .deadline(LocalDate.of(2024,06,16))
                .build());

        final ResultActions resultActions = mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON));

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].content").value(content))
                .andExpect(jsonPath("$[0].title").value(title));
    }

    @DisplayName("findProject: 아티클(id 검색) 글 조회 성공")
    @Test
    public void findProject() throws Exception{
        final String url = "/api/projects/{id}";

        final String title = "title";
        final String content = "content";

        Project savedProject = projectRepository.save(Project.builder()
                .title(title)
                .content(content)
                .deadline(LocalDate.of(2024,06,16))
                .build());


        final ResultActions resultActions = mockMvc.perform(get(url, savedProject.getId()));

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value(content))
                .andExpect(jsonPath("$.title").value(title));
    }

    @DisplayName("deleteProject: 아티클 삭제 성공")
    @Test
    public void deleteProject() throws Exception
    {
        final String url = "/api/projects/{id}";
        final String title = "title";
        final String content = "content";

        Project savedProject = projectRepository.save(Project.builder()
                .title(title)
                .content(content)
                .build());

        mockMvc.perform(delete(url, savedProject.getId()))
                .andExpect(status().isOk());

        List<Project> projects = projectRepository.findAll();

        assertThat(projects).isEmpty();
    }

    @DisplayName("updateProject: 아티클 글 수정 성공")
    @Test
    public void updateProject() throws Exception{
        final String url = "/api/projects/{id}";
        final String title = "title";
        final String content = "content";

        Project savedProject = projectRepository.save(Project.builder()
                .title(title)
                .content(content)
                .build());

        final String newTitle = "new title";
        final String newContent = "new content";

        UpdateProjectRequest request = new UpdateProjectRequest(newTitle, newContent,LocalDate.of(2024,06,16));

        ResultActions resultActions = mockMvc.perform(put(url, savedProject.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(request)));

        resultActions.andExpect(status().isOk());

        Project project = projectRepository.findById(savedProject.getId()).get();

        assertThat(project.getTitle()).isEqualTo(newTitle);
        assertThat(project.getContent()).isEqualTo(newContent);
    }
}