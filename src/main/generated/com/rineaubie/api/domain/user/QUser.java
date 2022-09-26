package com.rineaubie.api.domain.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.rineaubie.api.config.oauth.entity.Role;
import com.rineaubie.api.config.oauth.entity.UserPrincipal;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<UserPrincipal> {

    private static final long serialVersionUID = 914254519L;

    public static final QUser user = new QUser("user");

    public final com.rineaubie.api.domain.QBaseTimeEntity _super = new com.rineaubie.api.domain.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath name = createString("name");

    public final StringPath picture = createString("picture");

    public final EnumPath<Role> role = createEnum("role", Role.class);

    public QUser(String variable) {
        super(UserPrincipal.class, forVariable(variable));
    }

    public QUser(Path<? extends UserPrincipal> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(UserPrincipal.class, metadata);
    }

}

