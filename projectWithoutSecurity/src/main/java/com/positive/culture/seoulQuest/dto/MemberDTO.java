package com.positive.culture.seoulQuest.dto;//package com.positive.culture.seoulQuest.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
public class MemberDTO  {

    private String email;
    private String password;
    private String nickname;
    private boolean social;

    private List<String> roleNames = new ArrayList<>();


}
