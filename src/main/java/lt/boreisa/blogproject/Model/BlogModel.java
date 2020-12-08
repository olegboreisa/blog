package lt.boreisa.blogproject.Model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name ="blog")
public class BlogModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "blog_head")
    private String blogHead;

    @Lob
    @Column(name = "blog_text")
    private String blogText;
}
