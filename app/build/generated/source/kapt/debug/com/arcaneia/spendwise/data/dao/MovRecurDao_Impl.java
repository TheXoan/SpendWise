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
import com.arcaneia.spendwise.data.entity.MovRecur;
import com.arcaneia.spendwise.data.model.Recurrence;
import com.arcaneia.spendwise.data.model.TypeMov;
import java.lang.Class;
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
public final class MovRecurDao_Impl implements MovRecurDao {
  private final RoomDatabase __db;

  private final EntityInsertAdapter<MovRecur> __insertAdapterOfMovRecur;

  private final Converters __converters = new Converters();

  private final EntityDeleteOrUpdateAdapter<MovRecur> __deleteAdapterOfMovRecur;

  private final EntityDeleteOrUpdateAdapter<MovRecur> __updateAdapterOfMovRecur;

  public MovRecurDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertAdapterOfMovRecur = new EntityInsertAdapter<MovRecur>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `mov_recur` (`id`,`nome`,`importe`,`periodicidade`,`data_ini`,`data_rnv`,`tipo`,`remote_id`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final MovRecur entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getNome() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getNome());
        }
        statement.bindDouble(3, entity.getImporte());
        final String _tmp = __converters.fromRecurrence(entity.getPeriodicidade());
        if (_tmp == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, _tmp);
        }
        if (entity.getData_ini() == null) {
          statement.bindNull(5);
        } else {
          statement.bindText(5, entity.getData_ini());
        }
        if (entity.getData_rnv() == null) {
          statement.bindNull(6);
        } else {
          statement.bindText(6, entity.getData_rnv());
        }
        final String _tmp_1 = __converters.fromTipoMov(entity.getTipo());
        if (_tmp_1 == null) {
          statement.bindNull(7);
        } else {
          statement.bindText(7, _tmp_1);
        }
        if (entity.getRemote_id() == null) {
          statement.bindNull(8);
        } else {
          statement.bindText(8, entity.getRemote_id());
        }
      }
    };
    this.__deleteAdapterOfMovRecur = new EntityDeleteOrUpdateAdapter<MovRecur>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `mov_recur` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final MovRecur entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfMovRecur = new EntityDeleteOrUpdateAdapter<MovRecur>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `mov_recur` SET `id` = ?,`nome` = ?,`importe` = ?,`periodicidade` = ?,`data_ini` = ?,`data_rnv` = ?,`tipo` = ?,`remote_id` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final MovRecur entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getNome() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getNome());
        }
        statement.bindDouble(3, entity.getImporte());
        final String _tmp = __converters.fromRecurrence(entity.getPeriodicidade());
        if (_tmp == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, _tmp);
        }
        if (entity.getData_ini() == null) {
          statement.bindNull(5);
        } else {
          statement.bindText(5, entity.getData_ini());
        }
        if (entity.getData_rnv() == null) {
          statement.bindNull(6);
        } else {
          statement.bindText(6, entity.getData_rnv());
        }
        final String _tmp_1 = __converters.fromTipoMov(entity.getTipo());
        if (_tmp_1 == null) {
          statement.bindNull(7);
        } else {
          statement.bindText(7, _tmp_1);
        }
        if (entity.getRemote_id() == null) {
          statement.bindNull(8);
        } else {
          statement.bindText(8, entity.getRemote_id());
        }
        statement.bindLong(9, entity.getId());
      }
    };
  }

  @Override
  public Object insert(final MovRecur movRecur, final Continuation<? super Long> arg1) {
    if (movRecur == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      return __insertAdapterOfMovRecur.insertAndReturnId(_connection, movRecur);
    }, arg1);
  }

  @Override
  public Object delete(final MovRecur movRecur, final Continuation<? super Unit> arg1) {
    if (movRecur == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __deleteAdapterOfMovRecur.handle(_connection, movRecur);
      return Unit.INSTANCE;
    }, arg1);
  }

  @Override
  public Object update(final MovRecur movRecur, final Continuation<? super Unit> arg1) {
    if (movRecur == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __updateAdapterOfMovRecur.handle(_connection, movRecur);
      return Unit.INSTANCE;
    }, arg1);
  }

  @Override
  public Flow<List<MovRecur>> getAllMovRecur() {
    final String _sql = "SELECT * FROM mov_recur ORDER BY data_rnv ASC";
    return FlowUtil.createFlow(__db, false, new String[] {"mov_recur"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfNome = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "nome");
        final int _columnIndexOfImporte = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "importe");
        final int _columnIndexOfPeriodicidade = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "periodicidade");
        final int _columnIndexOfDataIni = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "data_ini");
        final int _columnIndexOfDataRnv = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "data_rnv");
        final int _columnIndexOfTipo = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "tipo");
        final int _columnIndexOfRemoteId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "remote_id");
        final List<MovRecur> _result = new ArrayList<MovRecur>();
        while (_stmt.step()) {
          final MovRecur _item;
          final int _tmpId;
          _tmpId = (int) (_stmt.getLong(_columnIndexOfId));
          final String _tmpNome;
          if (_stmt.isNull(_columnIndexOfNome)) {
            _tmpNome = null;
          } else {
            _tmpNome = _stmt.getText(_columnIndexOfNome);
          }
          final double _tmpImporte;
          _tmpImporte = _stmt.getDouble(_columnIndexOfImporte);
          final Recurrence _tmpPeriodicidade;
          final String _tmp;
          if (_stmt.isNull(_columnIndexOfPeriodicidade)) {
            _tmp = null;
          } else {
            _tmp = _stmt.getText(_columnIndexOfPeriodicidade);
          }
          _tmpPeriodicidade = __converters.toRecurrence(_tmp);
          final String _tmpData_ini;
          if (_stmt.isNull(_columnIndexOfDataIni)) {
            _tmpData_ini = null;
          } else {
            _tmpData_ini = _stmt.getText(_columnIndexOfDataIni);
          }
          final String _tmpData_rnv;
          if (_stmt.isNull(_columnIndexOfDataRnv)) {
            _tmpData_rnv = null;
          } else {
            _tmpData_rnv = _stmt.getText(_columnIndexOfDataRnv);
          }
          final TypeMov _tmpTipo;
          final String _tmp_1;
          if (_stmt.isNull(_columnIndexOfTipo)) {
            _tmp_1 = null;
          } else {
            _tmp_1 = _stmt.getText(_columnIndexOfTipo);
          }
          _tmpTipo = __converters.toTipoMov(_tmp_1);
          final String _tmpRemote_id;
          if (_stmt.isNull(_columnIndexOfRemoteId)) {
            _tmpRemote_id = null;
          } else {
            _tmpRemote_id = _stmt.getText(_columnIndexOfRemoteId);
          }
          _item = new MovRecur(_tmpId,_tmpNome,_tmpImporte,_tmpPeriodicidade,_tmpData_ini,_tmpData_rnv,_tmpTipo,_tmpRemote_id);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Object getMovsToRenew(final String today,
      final Continuation<? super List<MovRecur>> arg1) {
    final String _sql = "SELECT * FROM mov_recur WHERE data_rnv <= ?";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        if (today == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindText(_argIndex, today);
        }
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfNome = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "nome");
        final int _columnIndexOfImporte = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "importe");
        final int _columnIndexOfPeriodicidade = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "periodicidade");
        final int _columnIndexOfDataIni = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "data_ini");
        final int _columnIndexOfDataRnv = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "data_rnv");
        final int _columnIndexOfTipo = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "tipo");
        final int _columnIndexOfRemoteId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "remote_id");
        final List<MovRecur> _result = new ArrayList<MovRecur>();
        while (_stmt.step()) {
          final MovRecur _item;
          final int _tmpId;
          _tmpId = (int) (_stmt.getLong(_columnIndexOfId));
          final String _tmpNome;
          if (_stmt.isNull(_columnIndexOfNome)) {
            _tmpNome = null;
          } else {
            _tmpNome = _stmt.getText(_columnIndexOfNome);
          }
          final double _tmpImporte;
          _tmpImporte = _stmt.getDouble(_columnIndexOfImporte);
          final Recurrence _tmpPeriodicidade;
          final String _tmp;
          if (_stmt.isNull(_columnIndexOfPeriodicidade)) {
            _tmp = null;
          } else {
            _tmp = _stmt.getText(_columnIndexOfPeriodicidade);
          }
          _tmpPeriodicidade = __converters.toRecurrence(_tmp);
          final String _tmpData_ini;
          if (_stmt.isNull(_columnIndexOfDataIni)) {
            _tmpData_ini = null;
          } else {
            _tmpData_ini = _stmt.getText(_columnIndexOfDataIni);
          }
          final String _tmpData_rnv;
          if (_stmt.isNull(_columnIndexOfDataRnv)) {
            _tmpData_rnv = null;
          } else {
            _tmpData_rnv = _stmt.getText(_columnIndexOfDataRnv);
          }
          final TypeMov _tmpTipo;
          final String _tmp_1;
          if (_stmt.isNull(_columnIndexOfTipo)) {
            _tmp_1 = null;
          } else {
            _tmp_1 = _stmt.getText(_columnIndexOfTipo);
          }
          _tmpTipo = __converters.toTipoMov(_tmp_1);
          final String _tmpRemote_id;
          if (_stmt.isNull(_columnIndexOfRemoteId)) {
            _tmpRemote_id = null;
          } else {
            _tmpRemote_id = _stmt.getText(_columnIndexOfRemoteId);
          }
          _item = new MovRecur(_tmpId,_tmpNome,_tmpImporte,_tmpPeriodicidade,_tmpData_ini,_tmpData_rnv,_tmpTipo,_tmpRemote_id);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, arg1);
  }

  @Override
  public Object getById(final int id, final Continuation<? super MovRecur> arg1) {
    final String _sql = "SELECT * FROM mov_recur WHERE id = ? LIMIT 1";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, id);
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfNome = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "nome");
        final int _columnIndexOfImporte = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "importe");
        final int _columnIndexOfPeriodicidade = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "periodicidade");
        final int _columnIndexOfDataIni = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "data_ini");
        final int _columnIndexOfDataRnv = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "data_rnv");
        final int _columnIndexOfTipo = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "tipo");
        final int _columnIndexOfRemoteId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "remote_id");
        final MovRecur _result;
        if (_stmt.step()) {
          final int _tmpId;
          _tmpId = (int) (_stmt.getLong(_columnIndexOfId));
          final String _tmpNome;
          if (_stmt.isNull(_columnIndexOfNome)) {
            _tmpNome = null;
          } else {
            _tmpNome = _stmt.getText(_columnIndexOfNome);
          }
          final double _tmpImporte;
          _tmpImporte = _stmt.getDouble(_columnIndexOfImporte);
          final Recurrence _tmpPeriodicidade;
          final String _tmp;
          if (_stmt.isNull(_columnIndexOfPeriodicidade)) {
            _tmp = null;
          } else {
            _tmp = _stmt.getText(_columnIndexOfPeriodicidade);
          }
          _tmpPeriodicidade = __converters.toRecurrence(_tmp);
          final String _tmpData_ini;
          if (_stmt.isNull(_columnIndexOfDataIni)) {
            _tmpData_ini = null;
          } else {
            _tmpData_ini = _stmt.getText(_columnIndexOfDataIni);
          }
          final String _tmpData_rnv;
          if (_stmt.isNull(_columnIndexOfDataRnv)) {
            _tmpData_rnv = null;
          } else {
            _tmpData_rnv = _stmt.getText(_columnIndexOfDataRnv);
          }
          final TypeMov _tmpTipo;
          final String _tmp_1;
          if (_stmt.isNull(_columnIndexOfTipo)) {
            _tmp_1 = null;
          } else {
            _tmp_1 = _stmt.getText(_columnIndexOfTipo);
          }
          _tmpTipo = __converters.toTipoMov(_tmp_1);
          final String _tmpRemote_id;
          if (_stmt.isNull(_columnIndexOfRemoteId)) {
            _tmpRemote_id = null;
          } else {
            _tmpRemote_id = _stmt.getText(_columnIndexOfRemoteId);
          }
          _result = new MovRecur(_tmpId,_tmpNome,_tmpImporte,_tmpPeriodicidade,_tmpData_ini,_tmpData_rnv,_tmpTipo,_tmpRemote_id);
        } else {
          _result = null;
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, arg1);
  }

  @Override
  public Object getPendingToUpload(final Continuation<? super List<MovRecur>> arg0) {
    final String _sql = "SELECT * FROM mov_recur WHERE remote_id IS NULL";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfNome = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "nome");
        final int _columnIndexOfImporte = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "importe");
        final int _columnIndexOfPeriodicidade = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "periodicidade");
        final int _columnIndexOfDataIni = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "data_ini");
        final int _columnIndexOfDataRnv = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "data_rnv");
        final int _columnIndexOfTipo = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "tipo");
        final int _columnIndexOfRemoteId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "remote_id");
        final List<MovRecur> _result = new ArrayList<MovRecur>();
        while (_stmt.step()) {
          final MovRecur _item;
          final int _tmpId;
          _tmpId = (int) (_stmt.getLong(_columnIndexOfId));
          final String _tmpNome;
          if (_stmt.isNull(_columnIndexOfNome)) {
            _tmpNome = null;
          } else {
            _tmpNome = _stmt.getText(_columnIndexOfNome);
          }
          final double _tmpImporte;
          _tmpImporte = _stmt.getDouble(_columnIndexOfImporte);
          final Recurrence _tmpPeriodicidade;
          final String _tmp;
          if (_stmt.isNull(_columnIndexOfPeriodicidade)) {
            _tmp = null;
          } else {
            _tmp = _stmt.getText(_columnIndexOfPeriodicidade);
          }
          _tmpPeriodicidade = __converters.toRecurrence(_tmp);
          final String _tmpData_ini;
          if (_stmt.isNull(_columnIndexOfDataIni)) {
            _tmpData_ini = null;
          } else {
            _tmpData_ini = _stmt.getText(_columnIndexOfDataIni);
          }
          final String _tmpData_rnv;
          if (_stmt.isNull(_columnIndexOfDataRnv)) {
            _tmpData_rnv = null;
          } else {
            _tmpData_rnv = _stmt.getText(_columnIndexOfDataRnv);
          }
          final TypeMov _tmpTipo;
          final String _tmp_1;
          if (_stmt.isNull(_columnIndexOfTipo)) {
            _tmp_1 = null;
          } else {
            _tmp_1 = _stmt.getText(_columnIndexOfTipo);
          }
          _tmpTipo = __converters.toTipoMov(_tmp_1);
          final String _tmpRemote_id;
          if (_stmt.isNull(_columnIndexOfRemoteId)) {
            _tmpRemote_id = null;
          } else {
            _tmpRemote_id = _stmt.getText(_columnIndexOfRemoteId);
          }
          _item = new MovRecur(_tmpId,_tmpNome,_tmpImporte,_tmpPeriodicidade,_tmpData_ini,_tmpData_rnv,_tmpTipo,_tmpRemote_id);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, arg0);
  }

  @Override
  public Object getByRemoteId(final String remoteId, final Continuation<? super MovRecur> arg1) {
    final String _sql = "SELECT * FROM mov_recur WHERE remote_id = ? LIMIT 1";
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
        final int _columnIndexOfNome = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "nome");
        final int _columnIndexOfImporte = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "importe");
        final int _columnIndexOfPeriodicidade = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "periodicidade");
        final int _columnIndexOfDataIni = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "data_ini");
        final int _columnIndexOfDataRnv = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "data_rnv");
        final int _columnIndexOfTipo = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "tipo");
        final int _columnIndexOfRemoteId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "remote_id");
        final MovRecur _result;
        if (_stmt.step()) {
          final int _tmpId;
          _tmpId = (int) (_stmt.getLong(_columnIndexOfId));
          final String _tmpNome;
          if (_stmt.isNull(_columnIndexOfNome)) {
            _tmpNome = null;
          } else {
            _tmpNome = _stmt.getText(_columnIndexOfNome);
          }
          final double _tmpImporte;
          _tmpImporte = _stmt.getDouble(_columnIndexOfImporte);
          final Recurrence _tmpPeriodicidade;
          final String _tmp;
          if (_stmt.isNull(_columnIndexOfPeriodicidade)) {
            _tmp = null;
          } else {
            _tmp = _stmt.getText(_columnIndexOfPeriodicidade);
          }
          _tmpPeriodicidade = __converters.toRecurrence(_tmp);
          final String _tmpData_ini;
          if (_stmt.isNull(_columnIndexOfDataIni)) {
            _tmpData_ini = null;
          } else {
            _tmpData_ini = _stmt.getText(_columnIndexOfDataIni);
          }
          final String _tmpData_rnv;
          if (_stmt.isNull(_columnIndexOfDataRnv)) {
            _tmpData_rnv = null;
          } else {
            _tmpData_rnv = _stmt.getText(_columnIndexOfDataRnv);
          }
          final TypeMov _tmpTipo;
          final String _tmp_1;
          if (_stmt.isNull(_columnIndexOfTipo)) {
            _tmp_1 = null;
          } else {
            _tmp_1 = _stmt.getText(_columnIndexOfTipo);
          }
          _tmpTipo = __converters.toTipoMov(_tmp_1);
          final String _tmpRemote_id;
          if (_stmt.isNull(_columnIndexOfRemoteId)) {
            _tmpRemote_id = null;
          } else {
            _tmpRemote_id = _stmt.getText(_columnIndexOfRemoteId);
          }
          _result = new MovRecur(_tmpId,_tmpNome,_tmpImporte,_tmpPeriodicidade,_tmpData_ini,_tmpData_rnv,_tmpTipo,_tmpRemote_id);
        } else {
          _result = null;
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, arg1);
  }

  @Override
  public Object getAllWithRemoteId(final Continuation<? super List<MovRecur>> arg0) {
    final String _sql = "SELECT * FROM mov_recur WHERE remote_id IS NOT NULL";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfNome = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "nome");
        final int _columnIndexOfImporte = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "importe");
        final int _columnIndexOfPeriodicidade = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "periodicidade");
        final int _columnIndexOfDataIni = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "data_ini");
        final int _columnIndexOfDataRnv = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "data_rnv");
        final int _columnIndexOfTipo = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "tipo");
        final int _columnIndexOfRemoteId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "remote_id");
        final List<MovRecur> _result = new ArrayList<MovRecur>();
        while (_stmt.step()) {
          final MovRecur _item;
          final int _tmpId;
          _tmpId = (int) (_stmt.getLong(_columnIndexOfId));
          final String _tmpNome;
          if (_stmt.isNull(_columnIndexOfNome)) {
            _tmpNome = null;
          } else {
            _tmpNome = _stmt.getText(_columnIndexOfNome);
          }
          final double _tmpImporte;
          _tmpImporte = _stmt.getDouble(_columnIndexOfImporte);
          final Recurrence _tmpPeriodicidade;
          final String _tmp;
          if (_stmt.isNull(_columnIndexOfPeriodicidade)) {
            _tmp = null;
          } else {
            _tmp = _stmt.getText(_columnIndexOfPeriodicidade);
          }
          _tmpPeriodicidade = __converters.toRecurrence(_tmp);
          final String _tmpData_ini;
          if (_stmt.isNull(_columnIndexOfDataIni)) {
            _tmpData_ini = null;
          } else {
            _tmpData_ini = _stmt.getText(_columnIndexOfDataIni);
          }
          final String _tmpData_rnv;
          if (_stmt.isNull(_columnIndexOfDataRnv)) {
            _tmpData_rnv = null;
          } else {
            _tmpData_rnv = _stmt.getText(_columnIndexOfDataRnv);
          }
          final TypeMov _tmpTipo;
          final String _tmp_1;
          if (_stmt.isNull(_columnIndexOfTipo)) {
            _tmp_1 = null;
          } else {
            _tmp_1 = _stmt.getText(_columnIndexOfTipo);
          }
          _tmpTipo = __converters.toTipoMov(_tmp_1);
          final String _tmpRemote_id;
          if (_stmt.isNull(_columnIndexOfRemoteId)) {
            _tmpRemote_id = null;
          } else {
            _tmpRemote_id = _stmt.getText(_columnIndexOfRemoteId);
          }
          _item = new MovRecur(_tmpId,_tmpNome,_tmpImporte,_tmpPeriodicidade,_tmpData_ini,_tmpData_rnv,_tmpTipo,_tmpRemote_id);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, arg0);
  }

  @Override
  public Object attachRemoteId(final int localId, final String remoteId,
      final Continuation<? super Unit> arg2) {
    final String _sql = "UPDATE mov_recur SET remote_id = ? WHERE id = ?";
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
    }, arg2);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
