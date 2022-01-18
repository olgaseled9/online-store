package by.itacademy.javaenterprise.seledtsova.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class ItemPageDTO {

    private List<ItemDTO> items = new ArrayList<>();
    private List<Integer> numbersOfPages = new ArrayList<>();
    private Long pagesCount;
}
