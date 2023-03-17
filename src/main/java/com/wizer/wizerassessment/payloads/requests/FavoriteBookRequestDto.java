package com.wizer.wizerassessment.payloads.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FavoriteBookRequestDto {
    @ElementCollection
    private List<String> name;
}
