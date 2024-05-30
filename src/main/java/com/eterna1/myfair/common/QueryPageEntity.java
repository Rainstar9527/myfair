package com.eterna1.myfair.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryPageEntity {
    private Integer currentPage;
    private Integer pageSize;
    private String queryString;
}
