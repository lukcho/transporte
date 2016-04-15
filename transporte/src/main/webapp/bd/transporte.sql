
CREATE SEQUENCE seq_trans_lugares
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_trans_lugares
  OWNER TO postgres;

 CREATE SEQUENCE seq_trans_solicitud
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_trans_solicitud
  OWNER TO postgres;

/*==============================================================*/
/* Table: TRANS_CONDUCTORES                                     */
/*==============================================================*/
create table TRANS_CONDUCTORES (
   COND_CEDULA          VARCHAR(100)         not null,
   COND_NOMBRE          VARCHAR(100)         null,
   COND_APELLIDO        VARCHAR(100)         null,
   COND_TELEFONO        VARCHAR(10)          null,
   COND_ESTADO          CHAR(1)              null,
   constraint PK_TRANS_CONDUCTORES primary key (COND_CEDULA)
);

/*==============================================================*/
/* Table: TRANS_FUNCIONARIO_CONDUCTOR                           */
/*==============================================================*/
create table TRANS_FUNCIONARIO_CONDUCTOR (
   FCO_ID               VARCHAR(20)          not null,
   FCO_NOMBRES          VARCHAR(100)         null,
   FCO_GERENCIA         VARCHAR(200)         null,
   FCO_DIRECCION        VARCHAR(200)         null,
   FCO_ESTADO           CHAR(1)              null,
   constraint PK_TRANS_FUNCIONARIO_CONDUCTOR primary key (FCO_ID)
);

/*==============================================================*/
/* Table: TRANS_LUGARES                                         */
/*==============================================================*/
create table TRANS_LUGARES (
   LUG_ID               INT4                 not null DEFAULT nextval('seq_trans_lugares'::regclass),
   LUG_NOMBRE           VARCHAR(100)         null,
   LUG_CIUDAD           VARCHAR(100)         null,
   LUG_ESTADO           CHAR(1)              null,
   constraint PK_TRANS_LUGARES primary key (LUG_ID)
);

/*==============================================================*/
/* Table: TRANS_VEHICULO                                        */
/*==============================================================*/
create table TRANS_VEHICULO (
   VEHI_IDPLACA         VARCHAR(100)         not null,
   VEHI_NOMBRE          VARCHAR(100)         null,
   VEHI_MARCA           VARCHAR(100)         null,
   VEHI_MODELO          VARCHAR(100)         null,
   VEHI_TIPO            VARCHAR(100)         null,
   VEHI_CAPACIDAD       INT4                 null,
   VEHI_ESTADO          CHAR(1)              null,
   VEHI_ESTADO__FUNCIONAL CHAR(1)              null,
   constraint PK_TRANS_VEHICULO primary key (VEHI_IDPLACA)
);

/*==============================================================*/
/* Table: TRAN_SOLICITUD                                        */
/*==============================================================*/
create table TRANS_SOLICITUD (
   SOL_ID               INT4                 not null DEFAULT nextval('seq_trans_solicitud'::regclass),
   SOL_ID_SOLICITANTE   VARCHAR(100)          null,
   LUG_ID_ORIGEN        INT4                 null,
   LUG_ID_DESTINO       INT4                 null,
   FCO_ID               VARCHAR(20)          null,
   VEHI_IDPLACA         VARCHAR(100)         null,
   COND_CEDULA          VARCHAR(100)         null,
   SOL_FECHA            TIMESTAMP            null,
   SOL_FECHA_CREACION   TIMESTAMP            null,
   SOL_FECHA_APROBACION TIMESTAMP            null,
   SOL_PASAJEROS        INT4                 null,
   SOL_MOTIVO           VARCHAR(255)         null,
   SOL_HORA_INICIO      TIME                 null,
   SOL_HORA_FIN         TIME                 null,
   SOL_FLEXIBILIDAD     BOOL                 null,
   SOL_OBSERVACION      VARCHAR(255)         null,
   SOL_ESTADO           CHAR(1)              null,
   constraint PK_TRAN_SOLICITUD primary key (SOL_ID)
);

alter table TRANS_SOLICITUD
   add constraint FK_TRAN_SOL_REFERENCE_TRANS_LU_OR foreign key (LUG_ID_ORIGEN)
      references TRANS_LUGARES (LUG_ID)
      on delete restrict on update restrict;

alter table TRANS_SOLICITUD
   add constraint FK_TRAN_SOL_REFERENCE_TRANS_LU_DES foreign key (LUG_ID_DESTINO)
      references TRANS_LUGARES (LUG_ID)
      on delete restrict on update restrict;

alter table TRANS_SOLICITUD
   add constraint FK_TRAN_SOL_REFERENCE_TRANS_FU foreign key (FCO_ID)
      references TRANS_FUNCIONARIO_CONDUCTOR (FCO_ID)
      on delete restrict on update restrict;

alter table TRANS_SOLICITUD
   add constraint FK_TRAN_SOL_REFERENCE_TRANS_VE foreign key (VEHI_IDPLACA)
      references TRANS_VEHICULO (VEHI_IDPLACA)
      on delete restrict on update restrict;

alter table TRANS_SOLICITUD
   add constraint FK_TRAN_SOL_REFERENCE_TRANS_CO foreign key (COND_CEDULA)
      references TRANS_CONDUCTORES (COND_CEDULA)
      on delete restrict on update restrict;
