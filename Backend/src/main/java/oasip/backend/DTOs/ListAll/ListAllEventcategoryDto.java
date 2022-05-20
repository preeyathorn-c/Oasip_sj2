package oasip.backend.DTOs.ListAll;

import lombok.Data;

import java.io.Serializable;

@Data
public class ListAllEventcategoryDto implements Serializable {
    private Integer id;
    private String eventCategoryName;
    private String eventCategoryDescription;
    private Integer eventCategoryDuration;
}
