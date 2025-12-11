package com.arcaneia.spendwise.data.dao;

import androidx.annotation.NonNull;
import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.coroutines.FlowUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteStatement;
import com.arcaneia.spendwise.data.database.Converters;
import com.arcaneia.spendwise.data.entity.Mov;
import com.arcaneia.spendwise.data.entity.MovWithCategory;
import com.arcaneia.spendwise.data.model.TypeMov;
import java.lang.Class;
import java.lang.Double;
import java.lang.Integer;
import java.lang.Long;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation", "removal"})
public final class MovDao_Impl implements MovDao {
  private final RoomDatabase __db;

  private final EntityInsertAdapter<Mov> __insertAdapterOfMov;

  private final Converters __converters = new Converters();

  private final EntityDeleteOrUpdateAdapter<Mov> __deleteAdapterOfMov;

  private final EntityDeleteOrUpdateAdapter<Mov> __updateAdapterOfMov;

  public MovDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertAdapterOfMov = new EntityInsertAdapter<Mov>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `mov` (`id`,`tipo`,`importe`,`data_mov`,`descricion`,`categoria_id`,`mov_recur_id`,`remote_id`,`renew_hash`,`notificado`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement, @NonNull final Mov entity) {
        statement.bindLong(1, entity.getId());
        final String _tmp = __converters.fromTipoMov(entity.getTipo());
        if (_tmp == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, _tmp);
        }
        statement.bindDouble(3, entity.getImporte());
        if (entity.getData_mov() == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.getData_mov());
        }
        if (entity.getDescricion() == null) {
          statement.bindNull(5);
        } else {
          statement.bindText(5, entity.getDescricion());
        }
        statement.bindLong(6, entity.getCategoria_id());
        if (entity.getMov_recur_id() == null) {
          statement.bindNull(7);
        } else {
          statement.bindLong(7, entity.getMov_recur_id());
        }
        if (entity.getRemote_id() == null) {
          statement.bindNull(8);
        } else {
          statement.bindText(8, entity.getRemote_id());
        }
        if (entity.getRenew_hash() == null) {
          statement.bindNull(9);
        } else {
          statement.bindText(9, entity.getRenew_hash());
        }
        final int _tmp_1 = entity.getNotificado() ? 1 : 0;
        statement.bindLong(10, _tmp_1);
      }
    };
    this.__deleteAdapterOfMov = new EntityDeleteOrUpdateAdapter<Mov>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `mov` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement, @NonNull final Mov entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfMov = new EntityDeleteOrUpdateAdapter<Mov>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `mov` SET `id` = ?,`tipo` = ?,`importe` = ?,`data_mov` = ?,`descricion` = ?,`categoria_id` = ?,`mov_recur_id` = ?,`remote_id` = ?,`renew_hash` = ?,`notificado` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement, @NonNull final Mov entity) {
        statement.bindLong(1, entity.getId());
        final String _tmp = __converters.fromTipoMov(entity.getTipo());
        if (_tmp == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, _tmp);
        }
        statement.bindDouble(3, entity.getImporte());
        if (entity.getData_mov() == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.getData_mov());
        }
        if (entity.getDescricion() == null) {
          statement.bindNull(5);
        } else {
          statement.bindText(5, entity.getDescricion());
        }
        statement.bindLong(6, entity.getCategoria_id());
        if (entity.getMov_recur_id() == null) {
          statement.bindNull(7);
        } else {
          statement.bindLong(7, entity.getMov_recur_id());
        }
        if (entity.getRemote_id() == null) {
          statement.bindNull(8);
        } else {
          statement.bindText(8, entity.getRemote_id());
        }
        if (entity.getRenew_hash() == null) {
          statement.bindNull(9);
        } else {
          statement.bindText(9, entity.getRenew_hash());
        }
        final int _tmp_1 = entity.getNotificado() ? 1 : 0;
        statement.bindLong(10, _tmp_1);
        statement.bindLong(11, entity.getId());
      }
    };
  }

  @Override
  public Object insert(final Mov mov, final Continuation<? super Long> $completion) {
    if (mov == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      return __insertAdapterOfMov.insertAndReturnId(_connection, mov);
    }, $completion);
  }

  @Override
  public Object delete(final Mov mov, final Continuation<? super Unit> $completion) {
    if (mov == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __deleteAdapterOfMov.handle(_connection, mov);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object update(final Mov mov, final Continuation<? super Unit> $completion) {
    if (mov == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __updateAdapterOfMov.handle(_connection, mov);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Flow<Double> getBalanceMesActual() {
    final String _sql = "\n"
            + "        SELECT \n"
            + "            (IFNULL(SUM(CASE WHEN tipo = 'INGRESO' THEN importe END), 0) -\n"
            + "             IFNULL(SUM(CASE WHEN tipo = 'GASTO' THEN importe END), 0))\n"
            + "        FROM mov\n"
            + "        WHERE strftime('%Y-%m', data_mov) = strftime('%Y-%m', 'now', 'localtime')\n"
            + "    ";
    return FlowUtil.createFlow(__db, false, new String[] {"mov"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final Double _result;
        if (_stmt.step()) {
          final Double _tmp;
          if (_stmt.isNull(0)) {
            _tmp = null;
          } else {
            _tmp = _stmt.getDouble(0);
          }
          _result = _tmp;
        } else {
          _result = null;
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Flow<List<String>> getYearsWithValues() {
    final String _sql = "SELECT DISTINCT strftime('%Y', data_mov) AS year FROM mov ORDER BY year DESC";
    return FlowUtil.createFlow(__db, false, new String[] {"mov"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final List<String> _result = new ArrayList<String>();
        while (_stmt.step()) {
          final String _item;
          final String _tmp;
          if (_stmt.isNull(0)) {
            _tmp = null;
          } else {
            _tmp = _stmt.getText(0);
          }
          _item = _tmp;
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Flow<List<String>> getMonthsFromYear(final String year) {
    final String _sql = "\n"
            + "        SELECT DISTINCT strftime('%m', data_mov) AS month\n"
            + "        FROM mov\n"
            + "        WHERE strftime('%Y', data_mov) = ?\n"
            + "        ORDER BY month ASC\n"
            + "    ";
    return FlowUtil.createFlow(__db, false, new String[] {"mov"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (year == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, year);
        }
        final List<String> _result = new ArrayList<String>();
        while (_stmt.step()) {
          final String _item;
          final String _tmp;
          if (_stmt.isNull(0)) {
            _tmp = null;
          } else {
            _tmp = _stmt.getText(0);
          }
          _item = _tmp;
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Flow<List<MovWithCategory>> getMovementsForYearMonth(final String year,
      final String month) {
    final String _sql = "\n"
            + "        SELECT mov.*, categoria.nome AS categoriaNome\n"
            + "        FROM mov\n"
            + "        INNER JOIN categoria ON mov.categoria_id = categoria.id\n"
            + "        WHERE strftime('%Y', mov.data_mov) = ?\n"
            + "          AND strftime('%m', mov.data_mov) = ?\n"
            + "        ORDER BY mov.data_mov DESC\n"
            + "    ";
    return FlowUtil.createFlow(__db, false, new String[] {"mov", "categoria"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (year == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, year);
        }
        _argIndex = 2;
        if (month == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, month);
        }
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfTipo = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "tipo");
        final int _columnIndexOfImporte = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "importe");
        final int _columnIndexOfDataMov = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "data_mov");
        final int _columnIndexOfDescricion = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "descricion");
        final int _columnIndexOfCategoriaId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "categoria_id");
        final int _columnIndexOfMovRecurId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "mov_recur_id");
        final int _columnIndexOfRemoteId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "remote_id");
        final int _columnIndexOfRenewHash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "renew_hash");
        final int _columnIndexOfNotificado = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "notificado");
        final int _columnIndexOfCategoriaNome = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "categoriaNome");
        final List<MovWithCategory> _result = new ArrayList<MovWithCategory>();
        while (_stmt.step()) {
          final MovWithCategory _item;
          final String _tmpCategoriaNome;
          if (_stmt.isNull(_columnIndexOfCategoriaNome)) {
            _tmpCategoriaNome = null;
          } else {
            _tmpCategoriaNome = _stmt.getText(_columnIndexOfCategoriaNome);
          }
          final Mov _tmpMov;
          final int _tmpId;
          _tmpId = (int) (_stmt.getLong(_columnIndexOfId));
          final TypeMov _tmpTipo;
          final String _tmp;
          if (_stmt.isNull(_columnIndexOfTipo)) {
            _tmp = null;
          } else {
            _tmp = _stmt.getText(_columnIndexOfTipo);
          }
          _tmpTipo = __converters.toTipoMov(_tmp);
          final double _tmpImporte;
          _tmpImporte = _stmt.getDouble(_columnIndexOfImporte);
          final String _tmpData_mov;
          if (_stmt.isNull(_columnIndexOfDataMov)) {
            _tmpData_mov = null;
          } else {
            _tmpData_mov = _stmt.getText(_columnIndexOfDataMov);
          }
          final String _tmpDescricion;
          if (_stmt.isNull(_columnIndexOfDescricion)) {
            _tmpDescricion = null;
          } else {
            _tmpDescricion = _stmt.getText(_columnIndexOfDescricion);
          }
          final int _tmpCategoria_id;
          _tmpCategoria_id = (int) (_stmt.getLong(_columnIndexOfCategoriaId));
          final Integer _tmpMov_recur_id;
          if (_stmt.isNull(_columnIndexOfMovRecurId)) {
            _tmpMov_recur_id = null;
          } else {
            _tmpMov_recur_id = (int) (_stmt.getLong(_columnIndexOfMovRecurId));
          }
          final String _tmpRemote_id;
          if (_stmt.isNull(_columnIndexOfRemoteId)) {
            _tmpRemote_id = null;
          } else {
            _tmpRemote_id = _stmt.getText(_columnIndexOfRemoteId);
          }
          final String _tmpRenew_hash;
          if (_stmt.isNull(_columnIndexOfRenewHash)) {
            _tmpRenew_hash = null;
          } else {
            _tmpRenew_hash = _stmt.getText(_columnIndexOfRenewHash);
          }
          final boolean _tmpNotificado;
          final int _tmp_1;
          _tmp_1 = (int) (_stmt.getLong(_columnIndexOfNotificado));
          _tmpNotificado = _tmp_1 != 0;
          _tmpMov = new Mov(_tmpId,_tmpTipo,_tmpImporte,_tmpData_mov,_tmpDescricion,_tmpCategoria_id,_tmpMov_recur_id,_tmpRemote_id,_tmpRenew_hash,_tmpNotificado);
          _item = new MovWithCategory(_tmpMov,_tmpCategoriaNome);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Object getPendingToUpload(final Continuation<? super List<Mov>> $completion) {
    final String _sql = "SELECT * FROM mov WHERE remote_id IS NULL";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfTipo = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "tipo");
        final int _columnIndexOfImporte = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "importe");
        final int _columnIndexOfDataMov = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "data_mov");
        final int _columnIndexOfDescricion = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "descricion");
        final int _columnIndexOfCategoriaId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "categoria_id");
        final int _columnIndexOfMovRecurId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "mov_recur_id");
        final int _columnIndexOfRemoteId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "remote_id");
        final int _columnIndexOfRenewHash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "renew_hash");
        final int _columnIndexOfNotificado = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "notificado");
        final List<Mov> _result = new ArrayList<Mov>();
        while (_stmt.step()) {
          final Mov _item;
          final int _tmpId;
          _tmpId = (int) (_stmt.getLong(_columnIndexOfId));
          final TypeMov _tmpTipo;
          final String _tmp;
          if (_stmt.isNull(_columnIndexOfTipo)) {
            _tmp = null;
          } else {
            _tmp = _stmt.getText(_columnIndexOfTipo);
          }
          _tmpTipo = __converters.toTipoMov(_tmp);
          final double _tmpImporte;
          _tmpImporte = _stmt.getDouble(_columnIndexOfImporte);
          final String _tmpData_mov;
          if (_stmt.isNull(_columnIndexOfDataMov)) {
            _tmpData_mov = null;
          } else {
            _tmpData_mov = _stmt.getText(_columnIndexOfDataMov);
          }
          final String _tmpDescricion;
          if (_stmt.isNull(_columnIndexOfDescricion)) {
            _tmpDescricion = null;
          } else {
            _tmpDescricion = _stmt.getText(_columnIndexOfDescricion);
          }
          final int _tmpCategoria_id;
          _tmpCategoria_id = (int) (_stmt.getLong(_columnIndexOfCategoriaId));
          final Integer _tmpMov_recur_id;
          if (_stmt.isNull(_columnIndexOfMovRecurId)) {
            _tmpMov_recur_id = null;
          } else {
            _tmpMov_recur_id = (int) (_stmt.getLong(_columnIndexOfMovRecurId));
          }
          final String _tmpRemote_id;
          if (_stmt.isNull(_columnIndexOfRemoteId)) {
            _tmpRemote_id = null;
          } else {
            _tmpRemote_id = _stmt.getText(_columnIndexOfRemoteId);
          }
          final String _tmpRenew_hash;
          if (_stmt.isNull(_columnIndexOfRenewHash)) {
            _tmpRenew_hash = null;
          } else {
            _tmpRenew_hash = _stmt.getText(_columnIndexOfRenewHash);
          }
          final boolean _tmpNotificado;
          final int _tmp_1;
          _tmp_1 = (int) (_stmt.getLong(_columnIndexOfNotificado));
          _tmpNotificado = _tmp_1 != 0;
          _item = new Mov(_tmpId,_tmpTipo,_tmpImporte,_tmpData_mov,_tmpDescricion,_tmpCategoria_id,_tmpMov_recur_id,_tmpRemote_id,_tmpRenew_hash,_tmpNotificado);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object getByRemoteId(final String remoteId, final Continuation<? super Mov> $completion) {
    final String _sql = "SELECT * FROM mov WHERE remote_id = ? LIMIT 1";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (remoteId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, remoteId);
        }
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfTipo = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "tipo");
        final int _columnIndexOfImporte = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "importe");
        final int _columnIndexOfDataMov = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "data_mov");
        final int _columnIndexOfDescricion = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "descricion");
        final int _columnIndexOfCategoriaId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "categoria_id");
        final int _columnIndexOfMovRecurId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "mov_recur_id");
        final int _columnIndexOfRemoteId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "remote_id");
        final int _columnIndexOfRenewHash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "renew_hash");
        final int _columnIndexOfNotificado = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "notificado");
        final Mov _result;
        if (_stmt.step()) {
          final int _tmpId;
          _tmpId = (int) (_stmt.getLong(_columnIndexOfId));
          final TypeMov _tmpTipo;
          final String _tmp;
          if (_stmt.isNull(_columnIndexOfTipo)) {
            _tmp = null;
          } else {
            _tmp = _stmt.getText(_columnIndexOfTipo);
          }
          _tmpTipo = __converters.toTipoMov(_tmp);
          final double _tmpImporte;
          _tmpImporte = _stmt.getDouble(_columnIndexOfImporte);
          final String _tmpData_mov;
          if (_stmt.isNull(_columnIndexOfDataMov)) {
            _tmpData_mov = null;
          } else {
            _tmpData_mov = _stmt.getText(_columnIndexOfDataMov);
          }
          final String _tmpDescricion;
          if (_stmt.isNull(_columnIndexOfDescricion)) {
            _tmpDescricion = null;
          } else {
            _tmpDescricion = _stmt.getText(_columnIndexOfDescricion);
          }
          final int _tmpCategoria_id;
          _tmpCategoria_id = (int) (_stmt.getLong(_columnIndexOfCategoriaId));
          final Integer _tmpMov_recur_id;
          if (_stmt.isNull(_columnIndexOfMovRecurId)) {
            _tmpMov_recur_id = null;
          } else {
            _tmpMov_recur_id = (int) (_stmt.getLong(_columnIndexOfMovRecurId));
          }
          final String _tmpRemote_id;
          if (_stmt.isNull(_columnIndexOfRemoteId)) {
            _tmpRemote_id = null;
          } else {
            _tmpRemote_id = _stmt.getText(_columnIndexOfRemoteId);
          }
          final String _tmpRenew_hash;
          if (_stmt.isNull(_columnIndexOfRenewHash)) {
            _tmpRenew_hash = null;
          } else {
            _tmpRenew_hash = _stmt.getText(_columnIndexOfRenewHash);
          }
          final boolean _tmpNotificado;
          final int _tmp_1;
          _tmp_1 = (int) (_stmt.getLong(_columnIndexOfNotificado));
          _tmpNotificado = _tmp_1 != 0;
          _result = new Mov(_tmpId,_tmpTipo,_tmpImporte,_tmpData_mov,_tmpDescricion,_tmpCategoria_id,_tmpMov_recur_id,_tmpRemote_id,_tmpRenew_hash,_tmpNotificado);
        } else {
          _result = null;
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object getAllWithRemoteId(final Continuation<? super List<Mov>> $completion) {
    final String _sql = "SELECT * FROM mov WHERE remote_id IS NOT NULL";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfTipo = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "tipo");
        final int _columnIndexOfImporte = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "importe");
        final int _columnIndexOfDataMov = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "data_mov");
        final int _columnIndexOfDescricion = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "descricion");
        final int _columnIndexOfCategoriaId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "categoria_id");
        final int _columnIndexOfMovRecurId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "mov_recur_id");
        final int _columnIndexOfRemoteId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "remote_id");
        final int _columnIndexOfRenewHash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "renew_hash");
        final int _columnIndexOfNotificado = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "notificado");
        final List<Mov> _result = new ArrayList<Mov>();
        while (_stmt.step()) {
          final Mov _item;
          final int _tmpId;
          _tmpId = (int) (_stmt.getLong(_columnIndexOfId));
          final TypeMov _tmpTipo;
          final String _tmp;
          if (_stmt.isNull(_columnIndexOfTipo)) {
            _tmp = null;
          } else {
            _tmp = _stmt.getText(_columnIndexOfTipo);
          }
          _tmpTipo = __converters.toTipoMov(_tmp);
          final double _tmpImporte;
          _tmpImporte = _stmt.getDouble(_columnIndexOfImporte);
          final String _tmpData_mov;
          if (_stmt.isNull(_columnIndexOfDataMov)) {
            _tmpData_mov = null;
          } else {
            _tmpData_mov = _stmt.getText(_columnIndexOfDataMov);
          }
          final String _tmpDescricion;
          if (_stmt.isNull(_columnIndexOfDescricion)) {
            _tmpDescricion = null;
          } else {
            _tmpDescricion = _stmt.getText(_columnIndexOfDescricion);
          }
          final int _tmpCategoria_id;
          _tmpCategoria_id = (int) (_stmt.getLong(_columnIndexOfCategoriaId));
          final Integer _tmpMov_recur_id;
          if (_stmt.isNull(_columnIndexOfMovRecurId)) {
            _tmpMov_recur_id = null;
          } else {
            _tmpMov_recur_id = (int) (_stmt.getLong(_columnIndexOfMovRecurId));
          }
          final String _tmpRemote_id;
          if (_stmt.isNull(_columnIndexOfRemoteId)) {
            _tmpRemote_id = null;
          } else {
            _tmpRemote_id = _stmt.getText(_columnIndexOfRemoteId);
          }
          final String _tmpRenew_hash;
          if (_stmt.isNull(_columnIndexOfRenewHash)) {
            _tmpRenew_hash = null;
          } else {
            _tmpRenew_hash = _stmt.getText(_columnIndexOfRenewHash);
          }
          final boolean _tmpNotificado;
          final int _tmp_1;
          _tmp_1 = (int) (_stmt.getLong(_columnIndexOfNotificado));
          _tmpNotificado = _tmp_1 != 0;
          _item = new Mov(_tmpId,_tmpTipo,_tmpImporte,_tmpData_mov,_tmpDescricion,_tmpCategoria_id,_tmpMov_recur_id,_tmpRemote_id,_tmpRenew_hash,_tmpNotificado);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object getAll(final Continuation<? super List<Mov>> $completion) {
    final String _sql = "SELECT * FROM mov";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfTipo = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "tipo");
        final int _columnIndexOfImporte = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "importe");
        final int _columnIndexOfDataMov = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "data_mov");
        final int _columnIndexOfDescricion = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "descricion");
        final int _columnIndexOfCategoriaId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "categoria_id");
        final int _columnIndexOfMovRecurId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "mov_recur_id");
        final int _columnIndexOfRemoteId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "remote_id");
        final int _columnIndexOfRenewHash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "renew_hash");
        final int _columnIndexOfNotificado = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "notificado");
        final List<Mov> _result = new ArrayList<Mov>();
        while (_stmt.step()) {
          final Mov _item;
          final int _tmpId;
          _tmpId = (int) (_stmt.getLong(_columnIndexOfId));
          final TypeMov _tmpTipo;
          final String _tmp;
          if (_stmt.isNull(_columnIndexOfTipo)) {
            _tmp = null;
          } else {
            _tmp = _stmt.getText(_columnIndexOfTipo);
          }
          _tmpTipo = __converters.toTipoMov(_tmp);
          final double _tmpImporte;
          _tmpImporte = _stmt.getDouble(_columnIndexOfImporte);
          final String _tmpData_mov;
          if (_stmt.isNull(_columnIndexOfDataMov)) {
            _tmpData_mov = null;
          } else {
            _tmpData_mov = _stmt.getText(_columnIndexOfDataMov);
          }
          final String _tmpDescricion;
          if (_stmt.isNull(_columnIndexOfDescricion)) {
            _tmpDescricion = null;
          } else {
            _tmpDescricion = _stmt.getText(_columnIndexOfDescricion);
          }
          final int _tmpCategoria_id;
          _tmpCategoria_id = (int) (_stmt.getLong(_columnIndexOfCategoriaId));
          final Integer _tmpMov_recur_id;
          if (_stmt.isNull(_columnIndexOfMovRecurId)) {
            _tmpMov_recur_id = null;
          } else {
            _tmpMov_recur_id = (int) (_stmt.getLong(_columnIndexOfMovRecurId));
          }
          final String _tmpRemote_id;
          if (_stmt.isNull(_columnIndexOfRemoteId)) {
            _tmpRemote_id = null;
          } else {
            _tmpRemote_id = _stmt.getText(_columnIndexOfRemoteId);
          }
          final String _tmpRenew_hash;
          if (_stmt.isNull(_columnIndexOfRenewHash)) {
            _tmpRenew_hash = null;
          } else {
            _tmpRenew_hash = _stmt.getText(_columnIndexOfRenewHash);
          }
          final boolean _tmpNotificado;
          final int _tmp_1;
          _tmp_1 = (int) (_stmt.getLong(_columnIndexOfNotificado));
          _tmpNotificado = _tmp_1 != 0;
          _item = new Mov(_tmpId,_tmpTipo,_tmpImporte,_tmpData_mov,_tmpDescricion,_tmpCategoria_id,_tmpMov_recur_id,_tmpRemote_id,_tmpRenew_hash,_tmpNotificado);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object getById(final int id, final Continuation<? super Mov> $completion) {
    final String _sql = "SELECT * FROM mov WHERE id = ? LIMIT 1";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, id);
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfTipo = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "tipo");
        final int _columnIndexOfImporte = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "importe");
        final int _columnIndexOfDataMov = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "data_mov");
        final int _columnIndexOfDescricion = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "descricion");
        final int _columnIndexOfCategoriaId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "categoria_id");
        final int _columnIndexOfMovRecurId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "mov_recur_id");
        final int _columnIndexOfRemoteId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "remote_id");
        final int _columnIndexOfRenewHash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "renew_hash");
        final int _columnIndexOfNotificado = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "notificado");
        final Mov _result;
        if (_stmt.step()) {
          final int _tmpId;
          _tmpId = (int) (_stmt.getLong(_columnIndexOfId));
          final TypeMov _tmpTipo;
          final String _tmp;
          if (_stmt.isNull(_columnIndexOfTipo)) {
            _tmp = null;
          } else {
            _tmp = _stmt.getText(_columnIndexOfTipo);
          }
          _tmpTipo = __converters.toTipoMov(_tmp);
          final double _tmpImporte;
          _tmpImporte = _stmt.getDouble(_columnIndexOfImporte);
          final String _tmpData_mov;
          if (_stmt.isNull(_columnIndexOfDataMov)) {
            _tmpData_mov = null;
          } else {
            _tmpData_mov = _stmt.getText(_columnIndexOfDataMov);
          }
          final String _tmpDescricion;
          if (_stmt.isNull(_columnIndexOfDescricion)) {
            _tmpDescricion = null;
          } else {
            _tmpDescricion = _stmt.getText(_columnIndexOfDescricion);
          }
          final int _tmpCategoria_id;
          _tmpCategoria_id = (int) (_stmt.getLong(_columnIndexOfCategoriaId));
          final Integer _tmpMov_recur_id;
          if (_stmt.isNull(_columnIndexOfMovRecurId)) {
            _tmpMov_recur_id = null;
          } else {
            _tmpMov_recur_id = (int) (_stmt.getLong(_columnIndexOfMovRecurId));
          }
          final String _tmpRemote_id;
          if (_stmt.isNull(_columnIndexOfRemoteId)) {
            _tmpRemote_id = null;
          } else {
            _tmpRemote_id = _stmt.getText(_columnIndexOfRemoteId);
          }
          final String _tmpRenew_hash;
          if (_stmt.isNull(_columnIndexOfRenewHash)) {
            _tmpRenew_hash = null;
          } else {
            _tmpRenew_hash = _stmt.getText(_columnIndexOfRenewHash);
          }
          final boolean _tmpNotificado;
          final int _tmp_1;
          _tmp_1 = (int) (_stmt.getLong(_columnIndexOfNotificado));
          _tmpNotificado = _tmp_1 != 0;
          _result = new Mov(_tmpId,_tmpTipo,_tmpImporte,_tmpData_mov,_tmpDescricion,_tmpCategoria_id,_tmpMov_recur_id,_tmpRemote_id,_tmpRenew_hash,_tmpNotificado);
        } else {
          _result = null;
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Flow<List<Mov>> getAllFlow() {
    final String _sql = "SELECT * FROM mov";
    return FlowUtil.createFlow(__db, false, new String[] {"mov"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfTipo = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "tipo");
        final int _columnIndexOfImporte = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "importe");
        final int _columnIndexOfDataMov = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "data_mov");
        final int _columnIndexOfDescricion = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "descricion");
        final int _columnIndexOfCategoriaId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "categoria_id");
        final int _columnIndexOfMovRecurId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "mov_recur_id");
        final int _columnIndexOfRemoteId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "remote_id");
        final int _columnIndexOfRenewHash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "renew_hash");
        final int _columnIndexOfNotificado = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "notificado");
        final List<Mov> _result = new ArrayList<Mov>();
        while (_stmt.step()) {
          final Mov _item;
          final int _tmpId;
          _tmpId = (int) (_stmt.getLong(_columnIndexOfId));
          final TypeMov _tmpTipo;
          final String _tmp;
          if (_stmt.isNull(_columnIndexOfTipo)) {
            _tmp = null;
          } else {
            _tmp = _stmt.getText(_columnIndexOfTipo);
          }
          _tmpTipo = __converters.toTipoMov(_tmp);
          final double _tmpImporte;
          _tmpImporte = _stmt.getDouble(_columnIndexOfImporte);
          final String _tmpData_mov;
          if (_stmt.isNull(_columnIndexOfDataMov)) {
            _tmpData_mov = null;
          } else {
            _tmpData_mov = _stmt.getText(_columnIndexOfDataMov);
          }
          final String _tmpDescricion;
          if (_stmt.isNull(_columnIndexOfDescricion)) {
            _tmpDescricion = null;
          } else {
            _tmpDescricion = _stmt.getText(_columnIndexOfDescricion);
          }
          final int _tmpCategoria_id;
          _tmpCategoria_id = (int) (_stmt.getLong(_columnIndexOfCategoriaId));
          final Integer _tmpMov_recur_id;
          if (_stmt.isNull(_columnIndexOfMovRecurId)) {
            _tmpMov_recur_id = null;
          } else {
            _tmpMov_recur_id = (int) (_stmt.getLong(_columnIndexOfMovRecurId));
          }
          final String _tmpRemote_id;
          if (_stmt.isNull(_columnIndexOfRemoteId)) {
            _tmpRemote_id = null;
          } else {
            _tmpRemote_id = _stmt.getText(_columnIndexOfRemoteId);
          }
          final String _tmpRenew_hash;
          if (_stmt.isNull(_columnIndexOfRenewHash)) {
            _tmpRenew_hash = null;
          } else {
            _tmpRenew_hash = _stmt.getText(_columnIndexOfRenewHash);
          }
          final boolean _tmpNotificado;
          final int _tmp_1;
          _tmp_1 = (int) (_stmt.getLong(_columnIndexOfNotificado));
          _tmpNotificado = _tmp_1 != 0;
          _item = new Mov(_tmpId,_tmpTipo,_tmpImporte,_tmpData_mov,_tmpDescricion,_tmpCategoria_id,_tmpMov_recur_id,_tmpRemote_id,_tmpRenew_hash,_tmpNotificado);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Object getPendingNotifications(final Continuation<? super List<Mov>> $completion) {
    final String _sql = "SELECT * FROM mov WHERE notificado = 0 ORDER BY data_mov ASC";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfTipo = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "tipo");
        final int _columnIndexOfImporte = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "importe");
        final int _columnIndexOfDataMov = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "data_mov");
        final int _columnIndexOfDescricion = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "descricion");
        final int _columnIndexOfCategoriaId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "categoria_id");
        final int _columnIndexOfMovRecurId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "mov_recur_id");
        final int _columnIndexOfRemoteId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "remote_id");
        final int _columnIndexOfRenewHash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "renew_hash");
        final int _columnIndexOfNotificado = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "notificado");
        final List<Mov> _result = new ArrayList<Mov>();
        while (_stmt.step()) {
          final Mov _item;
          final int _tmpId;
          _tmpId = (int) (_stmt.getLong(_columnIndexOfId));
          final TypeMov _tmpTipo;
          final String _tmp;
          if (_stmt.isNull(_columnIndexOfTipo)) {
            _tmp = null;
          } else {
            _tmp = _stmt.getText(_columnIndexOfTipo);
          }
          _tmpTipo = __converters.toTipoMov(_tmp);
          final double _tmpImporte;
          _tmpImporte = _stmt.getDouble(_columnIndexOfImporte);
          final String _tmpData_mov;
          if (_stmt.isNull(_columnIndexOfDataMov)) {
            _tmpData_mov = null;
          } else {
            _tmpData_mov = _stmt.getText(_columnIndexOfDataMov);
          }
          final String _tmpDescricion;
          if (_stmt.isNull(_columnIndexOfDescricion)) {
            _tmpDescricion = null;
          } else {
            _tmpDescricion = _stmt.getText(_columnIndexOfDescricion);
          }
          final int _tmpCategoria_id;
          _tmpCategoria_id = (int) (_stmt.getLong(_columnIndexOfCategoriaId));
          final Integer _tmpMov_recur_id;
          if (_stmt.isNull(_columnIndexOfMovRecurId)) {
            _tmpMov_recur_id = null;
          } else {
            _tmpMov_recur_id = (int) (_stmt.getLong(_columnIndexOfMovRecurId));
          }
          final String _tmpRemote_id;
          if (_stmt.isNull(_columnIndexOfRemoteId)) {
            _tmpRemote_id = null;
          } else {
            _tmpRemote_id = _stmt.getText(_columnIndexOfRemoteId);
          }
          final String _tmpRenew_hash;
          if (_stmt.isNull(_columnIndexOfRenewHash)) {
            _tmpRenew_hash = null;
          } else {
            _tmpRenew_hash = _stmt.getText(_columnIndexOfRenewHash);
          }
          final boolean _tmpNotificado;
          final int _tmp_1;
          _tmp_1 = (int) (_stmt.getLong(_columnIndexOfNotificado));
          _tmpNotificado = _tmp_1 != 0;
          _item = new Mov(_tmpId,_tmpTipo,_tmpImporte,_tmpData_mov,_tmpDescricion,_tmpCategoria_id,_tmpMov_recur_id,_tmpRemote_id,_tmpRenew_hash,_tmpNotificado);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object getByRenewHash(final String hash, final Continuation<? super Mov> $completion) {
    final String _sql = "SELECT * FROM mov WHERE renew_hash = ? LIMIT 1";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (hash == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, hash);
        }
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfTipo = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "tipo");
        final int _columnIndexOfImporte = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "importe");
        final int _columnIndexOfDataMov = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "data_mov");
        final int _columnIndexOfDescricion = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "descricion");
        final int _columnIndexOfCategoriaId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "categoria_id");
        final int _columnIndexOfMovRecurId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "mov_recur_id");
        final int _columnIndexOfRemoteId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "remote_id");
        final int _columnIndexOfRenewHash = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "renew_hash");
        final int _columnIndexOfNotificado = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "notificado");
        final Mov _result;
        if (_stmt.step()) {
          final int _tmpId;
          _tmpId = (int) (_stmt.getLong(_columnIndexOfId));
          final TypeMov _tmpTipo;
          final String _tmp;
          if (_stmt.isNull(_columnIndexOfTipo)) {
            _tmp = null;
          } else {
            _tmp = _stmt.getText(_columnIndexOfTipo);
          }
          _tmpTipo = __converters.toTipoMov(_tmp);
          final double _tmpImporte;
          _tmpImporte = _stmt.getDouble(_columnIndexOfImporte);
          final String _tmpData_mov;
          if (_stmt.isNull(_columnIndexOfDataMov)) {
            _tmpData_mov = null;
          } else {
            _tmpData_mov = _stmt.getText(_columnIndexOfDataMov);
          }
          final String _tmpDescricion;
          if (_stmt.isNull(_columnIndexOfDescricion)) {
            _tmpDescricion = null;
          } else {
            _tmpDescricion = _stmt.getText(_columnIndexOfDescricion);
          }
          final int _tmpCategoria_id;
          _tmpCategoria_id = (int) (_stmt.getLong(_columnIndexOfCategoriaId));
          final Integer _tmpMov_recur_id;
          if (_stmt.isNull(_columnIndexOfMovRecurId)) {
            _tmpMov_recur_id = null;
          } else {
            _tmpMov_recur_id = (int) (_stmt.getLong(_columnIndexOfMovRecurId));
          }
          final String _tmpRemote_id;
          if (_stmt.isNull(_columnIndexOfRemoteId)) {
            _tmpRemote_id = null;
          } else {
            _tmpRemote_id = _stmt.getText(_columnIndexOfRemoteId);
          }
          final String _tmpRenew_hash;
          if (_stmt.isNull(_columnIndexOfRenewHash)) {
            _tmpRenew_hash = null;
          } else {
            _tmpRenew_hash = _stmt.getText(_columnIndexOfRenewHash);
          }
          final boolean _tmpNotificado;
          final int _tmp_1;
          _tmp_1 = (int) (_stmt.getLong(_columnIndexOfNotificado));
          _tmpNotificado = _tmp_1 != 0;
          _result = new Mov(_tmpId,_tmpTipo,_tmpImporte,_tmpData_mov,_tmpDescricion,_tmpCategoria_id,_tmpMov_recur_id,_tmpRemote_id,_tmpRenew_hash,_tmpNotificado);
        } else {
          _result = null;
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object attachRemoteId(final int localId, final String remoteId,
      final Continuation<? super Unit> $completion) {
    final String _sql = "UPDATE mov SET remote_id = ? WHERE id = ?";
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (remoteId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, remoteId);
        }
        _argIndex = 2;
        _stmt.bindLong(_argIndex, localId);
        _stmt.step();
        return Unit.INSTANCE;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object deleteByRemoteId(final String remoteId,
      final Continuation<? super Unit> $completion) {
    final String _sql = "DELETE FROM mov WHERE remote_id = ?";
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (remoteId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, remoteId);
        }
        _stmt.step();
        return Unit.INSTANCE;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @Override
  public Object markAsNotified(final int id, final Continuation<? super Unit> $completion) {
    final String _sql = "UPDATE mov SET notificado = 1 WHERE id = ?";
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, id);
        _stmt.step();
        return Unit.INSTANCE;
      } finally {
        _stmt.close();
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
