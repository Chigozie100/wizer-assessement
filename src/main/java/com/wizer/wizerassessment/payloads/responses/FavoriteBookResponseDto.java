package com.wizer.wizerassessment.payloads.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteBookResponseDto {
    @ElementCollection
    private List<String> name;
}
