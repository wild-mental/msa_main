package hana.corp.msa_main.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderRequest {
    private Long productId;
    private Integer quantity;
    private Long userId;
}
