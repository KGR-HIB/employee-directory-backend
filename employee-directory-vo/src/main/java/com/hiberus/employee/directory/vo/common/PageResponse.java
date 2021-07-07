package com.hiberus.employee.directory.vo.common;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

/**
 * Page to response paging data
 *
 * @author bcueva
 * @version 1.0
 * @param <T> Type data
 */
@Builder
@Getter
public class PageResponse<T> {

    /**
     * Data of page
     */
    private List<T> data;

    /**
     * Total items
     */
    private Long total;

    /**
     * Current page number
     */
    private Integer currentPage;

    /**
     * Total pages
     */
    private Integer totalPages;
}
