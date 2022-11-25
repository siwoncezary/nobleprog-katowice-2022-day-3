package pl.vattenfall.app.data.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.pl.PESEL;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import java.io.Serializable;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Serializable {
    public static long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    @Length(min = 3, max = 200, message = "Tytuł musi mie conajmniej 3 znaki!")
    private String title;

    @Getter
    @Setter
    @Length(min = 3, max = 50, message = "Autor musi mieć co najmniej 3 znaki!")
    private String author;

    @Getter
    @Setter
    @Range(min = 1900, max = 2050, message = "Rok wydania musi być miedzy 1900 a 2050")
    private int editionYear;


}
