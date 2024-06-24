package edm.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "organizations")
public class Organization {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String physicalAddress;

    private String legalAddress;

    private String head;

}
