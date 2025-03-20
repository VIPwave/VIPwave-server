package kr.vipwave.server.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "one_click")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OneClick {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "platform_id")
    private Platform platform;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "chart_type", length = 50)
    private ChartType chartType;

    @OneToMany(mappedBy = "oneClick")
    private List<Link> links;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
