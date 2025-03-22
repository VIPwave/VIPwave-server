package kr.vipwave.server.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "oneclick_links")
public class OneClickLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "device_type", length = 50)
    private DeviceType deviceType;

    private String url;

    @ManyToOne
    @JoinColumn(name = "oneclick_id")
    private OneClick oneClick;

    @Column(name = "link_order")
    private Integer linkOrder;
}
