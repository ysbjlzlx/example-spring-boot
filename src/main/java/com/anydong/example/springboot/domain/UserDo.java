package com.anydong.example.springboot.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.anydong.example.springboot.domain.converter.MobilePhoneConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.IOException;
import java.util.Date;

/**
 * @author Where
 */
@Entity
@Data
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public class UserDo {
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    @Column(length = 36)
    private String id;
    private String email;
    private String password;
    private String nickname;
    private String avatar;
    @Convert(converter = MobilePhoneConverter.class)
    @Column(columnDefinition = "JSON NULL")
    private MobilePhone phone;
    @JSONField(serialize = false, deserialize = false)
    @Column(columnDefinition = "POINT")
    private Point location;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdAt;
    @LastModifiedDate
    @Column(nullable = false)
    private Date updatedAt;

    @Data
    public static class MobilePhone {
        private String iddCode;
        private String phoneNumber;

        public MobilePhone(String iddCode, String phoneNumber) {
            this.iddCode = iddCode;
            this.phoneNumber = phoneNumber;
        }
    }

    @JSONField(name = "location")
    public String getLocationString() throws IOException {
        if (this.getLocation() != null) {
            return this.getLocation().toString();
        }
        return null;
    }

    @JSONField(name = "location")
    public void setLocation(String locationString) throws ParseException {
        WKTReader wktReader = new WKTReader();
        this.location = (Point) wktReader.read(locationString);
    }

    @JSONField(name = "location")
    public void setLocation(Point point) {
        this.location = point;
    }
}
