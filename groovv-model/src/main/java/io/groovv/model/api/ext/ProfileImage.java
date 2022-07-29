package io.groovv.model.api.ext;

import io.groovv.model.api.core.AbstractEntity;
import io.sunshower.persistence.id.Identifier;
import io.sunshower.persistence.id.Identifiers;
import io.sunshower.persistence.id.Sequence;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * when we hit a bajillion users we should upload these to a bucket-storage option (S3, Google
 * Storage). Not worrying about it for now
 */
@Entity
@Table(name = "PROFILE_IMAGES")
public class ProfileImage extends AbstractSerializableEntity<Identifier> {

  static final Sequence<Identifier> SEQUENCE;

  static {
    SEQUENCE = Identifiers.newSequence(true);
  }

  public ProfileImage() {
    super(SEQUENCE.next());
  }

  @Setter
  @Getter(
      onMethod =
          @__({
            @MapsId,
            @OneToOne,
            @PrimaryKeyJoinColumn(name = "owner_id", referencedColumnName = "id")
          }))
  private UserProfile owner;

  @Setter
  @Getter(onMethod = @__({@Lob, @Basic, @Column(name = "data")}))
  private byte[] data;
}
