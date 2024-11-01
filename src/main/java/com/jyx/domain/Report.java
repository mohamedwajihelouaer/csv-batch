package com.jyx.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Report {

    private String writeSkipCount;
    private String commitCount;
    private String writeCount;
    private String readCount;
    private String processSkipCount;
    private String readSkipCount;
    private String rollbackCount;
    private String filterCount;

}
