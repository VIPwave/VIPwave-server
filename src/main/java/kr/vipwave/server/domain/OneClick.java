package kr.vipwave.server.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "oneclick")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OneClick {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "platform_id")
    private Platform platform;

    @OrderBy("linkOrder ASC")
    @OneToMany(mappedBy = "oneClick", cascade = CascadeType.ALL)
    private List<OneClickLink> links;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
