package br.com.carloscesargsf.candidatecase.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class PaginatedItemsDTO<D> implements Serializable {

    private static final long serialVersionUID = -2323798840347094247L;

    @Schema(description = "List of requested elements.")
    private List<D> content;

    @Schema(description = "Requested page.", example = "0")
    private Integer pageNumber;

    @Schema(description = "Requested number of elements per page.", example = "10")
    private Integer pageSize;

    @Schema(description = "Total amount of elements.", example = "2")
    private Long totalElements;

    @Schema(description = "Total amount of pages based on current pageSize.", example = "1")
    private Integer totalPages;

    public PaginatedItemsDTO(Page<D> page) {
        content = page.stream().collect(Collectors.toList());
        pageNumber = page.getNumber();
        pageSize = page.getSize();
        totalElements = page.getTotalElements();
        totalPages = page.getTotalPages();
    }

    public List<D> getContent() {
        return content;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    @Override
    public String toString() {
        return "PaginatedItemsDTO{" +
                "content=" + content +
                ", pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                ", totalElements=" + totalElements +
                ", totalPages=" + totalPages +
                '}';
    }

}
