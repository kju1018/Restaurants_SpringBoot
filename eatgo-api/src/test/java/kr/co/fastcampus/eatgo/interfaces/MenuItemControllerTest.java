package kr.co.fastcampus.eatgo.interfaces;

import kr.co.fastcampus.eatgo.application.MenuitemService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MenuItemController.class)
class MenuItemControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MenuitemService menuitemService;

    @Test
    public void bulkUpdate() throws Exception {


        mvc.perform(patch("/restaurants/1/menuitems")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[]"))
                .andExpect(status().isOk());

        verify(menuitemService).bulkUpdate(eq(01L), any());
//        verify(menuitemService).bulkUpdate(1004L, new ArrayList<>());
    }

}