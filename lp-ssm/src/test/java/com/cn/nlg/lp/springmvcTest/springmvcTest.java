package com.cn.nlg.lp.springmvcTest;


import com.cn.nlg.lp.ssm.springmvc.SpringMvcConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringMvcConfiguration.class})
@WebAppConfiguration
public class springmvcTest {

    @Autowired
    WebApplicationContext wac;
    @Autowired
    MockHttpSession mockHttpSession;
    @Autowired
    MockHttpServletRequest mockHttpServletRequest;
    private MockMvc mockMvc;

    @Before
    public void before() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testNormalDispather() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("/index"))
                .andExpect(forwardedUrl("/views/index.jsp"))
//                .andExpect(content().contentType("text/plain;charset=UTF-8"))

        ;
    }
}
