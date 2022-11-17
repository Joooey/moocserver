package com.buaa.moocserver;

import com.buaa.moocserver.vo.request.ModelRequestVO;
import com.buaa.moocserver.vo.response.ModelResponseVO;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * @author Joey
 * @date 2022/11/16
 */
public class SendRequest {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        ModelRequestVO requestVO = new ModelRequestVO();
        requestVO.setSeq(Arrays.asList(1, 2, 3));
        ModelResponseVO responseVO = restTemplate.postForObject(
                "http://localhost:5000/recommend", requestVO, ModelResponseVO.class);
        System.out.println(responseVO);

    }
}
