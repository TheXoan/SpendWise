package com.arcaneia.spendwise.data.dao;

import androidx.annotation.NonNull;
import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.coroutines.FlowUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteStatement;
import com.arcaneia.spendwise.data.entity.Categoria;
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
public final class CategoriaDao_Impl implements CategoriaDao {
  private final RoomDatabase __db;

  private final EntityInsertAdapter<Categoria> __insertAdapterOfCategoria;

  private final EntityDeleteOrUpdateAdapter<Categoria> __deleteAdapterOfCategoria;

  private final EntityDeleteOrUpdateAdapter<Categoria> __updateAdapterOfCategoria;

  public CategoriaDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertAdapterOfCategoria = new EntityInsertAdapter<Categoria>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `categoria` (`id`,`nome`,`tipo`,`remote_id`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final Categoria entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getNome() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getNome());
        }
        if (entity.getTipo() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getTipo());
        }
        if (entity.getRemote_id() == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.getRemote_id());
        }
      }
    };
    this.__deleteAdapterOfCategoria = new EntityDeleteOrUpdateAdapter<Categoria>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `categoria` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final Categoria entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfCategoria = new EntityDeleteOrUpdateAdapter<Categoria>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `categoria` SET `id` = ?,`nome` = ?,`tipo` = ?,`remote_id` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final Categoria entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getNome() == null) {
          statement.bindNull(2);
        } else {
          statement.bindText(2, entity.getNome());
        }
        if (entity.getTipo() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getTipo());
        }
        if (entity.getRemote_id() == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.getRemote_id());
        }
        statement.bindLong(5, entity.getId());
      }
    };
  }

  @Override
  public Object insert(final Categoria categoria, final Continuation<? super Long> arg1) {
    if (categoria == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      return __insertAdapterOfCategoria.insertAndReturnId(_connection, categoria);
    }, arg1);
  }

  @Override
  public Object delete(final Categoria categoria, final Continuation<? super Unit> arg1) {
    if (categoria == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __deleteAdapterOfCategoria.handle(_connection, categoria);
      return Unit.INSTANCE;
    }, arg1);
  }

  @Override
  public Object update(final Categoria categoria, final Continuation<? super Unit> arg1) {
    if (categoria == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __updateAdapterOfCategoria.handle(_connection, categoria);
      return Unit.INSTANCE;
    }, arg1);
  }

  @Override
  public Flow<List<Categoria>> getAllCategories() {
    final String _sql = "SELECT * FROM categoria WHERE id != 1 ORDER BY id ASC";
    return FlowUtil.createFlow(__db, false, new String[] {"categoria"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfNome = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "nome");
        final int _columnIndexOfTipo = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "tipo");
        final int _columnIndexOfRemoteId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "remote_id");
        final List<Categoria> _result = new ArrayList<Categoria>();
        while (_stmt.step()) {
          final Categoria _item;
          final int _tmpId;
          _tmpId = (int) (_stmt.getLong(_columnIndexOfId));
          final String _tmpNome;
          if (_stmt.isNull(_columnIndexOfNome)) {
            _tmpNome = null;
          } else {
            _tmpNome = _stmt.getText(_columnIndexOfNome);
          }
          final String _tmpTipo;
          if (_stmt.isNull(_columnIndexOfTipo)) {
            _tmpTipo = null;
          } else {
            _tmpTipo = _stmt.getText(_columnIndexOfTipo);
          }
          final String _tmpRemote_id;
          if (_stmt.isNull(_columnIndexOfRemoteId)) {
            _tmpRemote_id = null;
          } else {
            _tmpRemote_id = _stmt.getText(_columnIndexOfRemoteId);
          }
          _item = new Categoria(_tmpId,_tmpNome,_tmpTipo,_tmpRemote_id);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @Override
  public Object getById(final int id, final Continuation<? super Categoria> arg1) {
    final String _sql = "SELECT * FROM categoria WHERE id = ? LIMIT 1";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, id);
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfNome = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "nome");
        final int _columnIndexOfTipo = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "tipo");
        final int _columnIndexOfRemoteId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "remote_id");
        final Categoria _result;
        if (_stmt.step()) {
          final int _tmpId;
          _tmpId = (int) (_stmt.getLong(_columnIndexOfId));
          final String _tmpNome;
          if (_stmt.isNull(_columnIndexOfNome)) {
            _tmpNome = null;
          } else {
            _tmpNome = _stmt.getText(_columnIndexOfNome);
          }
          final String _tmpTipo;
          if (_stmt.isNull(_columnIndexOfTipo)) {
            _tmpTipo = null;
          } else {
            _tmpTipo = _stmt.getText(_columnIndexOfTipo);
          }
          final String _tmpRemote_id;
          if (_stmt.isNull(_columnIndexOfRemoteId)) {
            _tmpRemote_id = null;
          } else {
            _tmpRemote_id = _stmt.getText(_columnIndexOfRemoteId);
          }
          _result = new Categoria(_tmpId,_tmpNome,_tmpTipo,_tmpRemote_id);
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
  public Object getPendingToUpload(final Continuation<? super List<Categoria>> arg0) {
    final String _sql = "SELECT * FROM categoria WHERE remote_id IS NULL";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfNome = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "nome");
        final int _columnIndexOfTipo = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "tipo");
        final int _columnIndexOfRemoteId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "remote_id");
        final List<Categoria> _result = new ArrayList<Categoria>();
        while (_stmt.step()) {
          final Categoria _item;
          final int _tmpId;
          _tmpId = (int) (_stmt.getLong(_columnIndexOfId));
          final String _tmpNome;
          if (_stmt.isNull(_columnIndexOfNome)) {
            _tmpNome = null;
          } else {
            _tmpNome = _stmt.getText(_columnIndexOfNome);
          }
          final String _tmpTipo;
          if (_stmt.isNull(_columnIndexOfTipo)) {
            _tmpTipo = null;
          } else {
            _tmpTipo = _stmt.getText(_columnIndexOfTipo);
          }
          final String _tmpRemote_id;
          if (_stmt.isNull(_columnIndexOfRemoteId)) {
            _tmpRemote_id = null;
          } else {
            _tmpRemote_id = _stmt.getText(_columnIndexOfRemoteId);
          }
          _item = new Categoria(_tmpId,_tmpNome,_tmpTipo,_tmpRemote_id);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, arg0);
  }

  @Override
  public Object getByRemoteId(final String remoteId, final Continuation<? super Categoria> arg1) {
    final String _sql = "SELECT * FROM categoria WHERE remote_id = ? LIMIT 1";
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
        final int _columnIndexOfTipo = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "tipo");
        final int _columnIndexOfRemoteId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "remote_id");
        final Categoria _result;
        if (_stmt.step()) {
          final int _tmpId;
          _tmpId = (int) (_stmt.getLong(_columnIndexOfId));
          final String _tmpNome;
          if (_stmt.isNull(_columnIndexOfNome)) {
            _tmpNome = null;
          } else {
            _tmpNome = _stmt.getText(_columnIndexOfNome);
          }
          final String _tmpTipo;
          if (_stmt.isNull(_columnIndexOfTipo)) {
            _tmpTipo = null;
          } else {
            _tmpTipo = _stmt.getText(_columnIndexOfTipo);
          }
          final String _tmpRemote_id;
          if (_stmt.isNull(_columnIndexOfRemoteId)) {
            _tmpRemote_id = null;
          } else {
            _tmpRemote_id = _stmt.getText(_columnIndexOfRemoteId);
          }
          _result = new Categoria(_tmpId,_tmpNome,_tmpTipo,_tmpRemote_id);
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
  public Object getAllWithRemoteId(final Continuation<? super List<Categoria>> arg0) {
    final String _sql = "SELECT * FROM categoria WHERE remote_id IS NOT NULL";
    return DBUtil.performSuspending(__db, true, false, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfNome = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "nome");
        final int _columnIndexOfTipo = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "tipo");
        final int _columnIndexOfRemoteId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "remote_id");
        final List<Categoria> _result = new ArrayList<Categoria>();
        while (_stmt.step()) {
          final Categoria _item;
          final int _tmpId;
          _tmpId = (int) (_stmt.getLong(_columnIndexOfId));
          final String _tmpNome;
          if (_stmt.isNull(_columnIndexOfNome)) {
            _tmpNome = null;
          } else {
            _tmpNome = _stmt.getText(_columnIndexOfNome);
          }
          final String _tmpTipo;
          if (_stmt.isNull(_columnIndexOfTipo)) {
            _tmpTipo = null;
          } else {
            _tmpTipo = _stmt.getText(_columnIndexOfTipo);
          }
          final String _tmpRemote_id;
          if (_stmt.isNull(_columnIndexOfRemoteId)) {
            _tmpRemote_id = null;
          } else {
            _tmpRemote_id = _stmt.getText(_columnIndexOfRemoteId);
          }
          _item = new Categoria(_tmpId,_tmpNome,_tmpTipo,_tmpRemote_id);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    }, arg0);
  }

  @Override
  public Object deleteById(final int categoriaId, final Continuation<? super Unit> arg1) {
    final String _sql = "DELETE FROM categoria WHERE id = ?";
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, categoriaId);
        _stmt.step();
        return Unit.INSTANCE;
      } finally {
        _stmt.close();
      }
    }, arg1);
  }

  @Override
  public Object attachRemoteId(final int localId, final String remoteId,
      final Continuation<? super Unit> arg2) {
    final String _sql = "UPDATE categoria SET remote_id = ? WHERE id = ?";
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
