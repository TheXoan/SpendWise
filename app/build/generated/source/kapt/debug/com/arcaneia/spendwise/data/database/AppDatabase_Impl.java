package com.arcaneia.spendwise.data.database;

import androidx.annotation.NonNull;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenDelegate;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.SQLite;
import androidx.sqlite.SQLiteConnection;
import com.arcaneia.spendwise.data.dao.CategoriaDao;
import com.arcaneia.spendwise.data.dao.CategoriaDao_Impl;
import com.arcaneia.spendwise.data.dao.MovDao;
import com.arcaneia.spendwise.data.dao.MovDao_Impl;
import com.arcaneia.spendwise.data.dao.MovRecurDao;
import com.arcaneia.spendwise.data.dao.MovRecurDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation", "removal"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile CategoriaDao _categoriaDao;

  private volatile MovDao _movDao;

  private volatile MovRecurDao _movRecurDao;

  @Override
  @NonNull
  protected RoomOpenDelegate createOpenDelegate() {
    final RoomOpenDelegate _openDelegate = new RoomOpenDelegate(1, "e95c7366ca9f4d187b024c16ddc8f3fa", "ec71991d43fa963df4cc233a7076a54f") {
      @Override
      public void createAllTables(@NonNull final SQLiteConnection connection) {
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `categoria` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `nome` TEXT NOT NULL, `tipo` TEXT NOT NULL, `remote_id` TEXT)");
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `mov` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `tipo` TEXT, `importe` REAL NOT NULL, `data_mov` TEXT NOT NULL, `descricion` TEXT, `categoria_id` INTEGER NOT NULL, `mov_recur_id` INTEGER, `remote_id` TEXT, `renew_hash` TEXT, `notificado` INTEGER NOT NULL, FOREIGN KEY(`categoria_id`) REFERENCES `categoria`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`mov_recur_id`) REFERENCES `mov_recur`(`id`) ON UPDATE NO ACTION ON DELETE SET NULL )");
        SQLite.execSQL(connection, "CREATE INDEX IF NOT EXISTS `index_mov_categoria_id` ON `mov` (`categoria_id`)");
        SQLite.execSQL(connection, "CREATE INDEX IF NOT EXISTS `index_mov_mov_recur_id` ON `mov` (`mov_recur_id`)");
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `mov_recur` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `nome` TEXT NOT NULL, `importe` REAL NOT NULL, `periodicidade` TEXT, `data_ini` TEXT NOT NULL, `data_rnv` TEXT NOT NULL, `tipo` TEXT, `remote_id` TEXT)");
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        SQLite.execSQL(connection, "INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'e95c7366ca9f4d187b024c16ddc8f3fa')");
      }

      @Override
      public void dropAllTables(@NonNull final SQLiteConnection connection) {
        SQLite.execSQL(connection, "DROP TABLE IF EXISTS `categoria`");
        SQLite.execSQL(connection, "DROP TABLE IF EXISTS `mov`");
        SQLite.execSQL(connection, "DROP TABLE IF EXISTS `mov_recur`");
      }

      @Override
      public void onCreate(@NonNull final SQLiteConnection connection) {
      }

      @Override
      public void onOpen(@NonNull final SQLiteConnection connection) {
        SQLite.execSQL(connection, "PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(connection);
      }

      @Override
      public void onPreMigrate(@NonNull final SQLiteConnection connection) {
        DBUtil.dropFtsSyncTriggers(connection);
      }

      @Override
      public void onPostMigrate(@NonNull final SQLiteConnection connection) {
      }

      @Override
      @NonNull
      public RoomOpenDelegate.ValidationResult onValidateSchema(
          @NonNull final SQLiteConnection connection) {
        final Map<String, TableInfo.Column> _columnsCategoria = new HashMap<String, TableInfo.Column>(4);
        _columnsCategoria.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCategoria.put("nome", new TableInfo.Column("nome", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCategoria.put("tipo", new TableInfo.Column("tipo", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCategoria.put("remote_id", new TableInfo.Column("remote_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final Set<TableInfo.ForeignKey> _foreignKeysCategoria = new HashSet<TableInfo.ForeignKey>(0);
        final Set<TableInfo.Index> _indicesCategoria = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCategoria = new TableInfo("categoria", _columnsCategoria, _foreignKeysCategoria, _indicesCategoria);
        final TableInfo _existingCategoria = TableInfo.read(connection, "categoria");
        if (!_infoCategoria.equals(_existingCategoria)) {
          return new RoomOpenDelegate.ValidationResult(false, "categoria(com.arcaneia.spendwise.data.entity.Categoria).\n"
                  + " Expected:\n" + _infoCategoria + "\n"
                  + " Found:\n" + _existingCategoria);
        }
        final Map<String, TableInfo.Column> _columnsMov = new HashMap<String, TableInfo.Column>(10);
        _columnsMov.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMov.put("tipo", new TableInfo.Column("tipo", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMov.put("importe", new TableInfo.Column("importe", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMov.put("data_mov", new TableInfo.Column("data_mov", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMov.put("descricion", new TableInfo.Column("descricion", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMov.put("categoria_id", new TableInfo.Column("categoria_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMov.put("mov_recur_id", new TableInfo.Column("mov_recur_id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMov.put("remote_id", new TableInfo.Column("remote_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMov.put("renew_hash", new TableInfo.Column("renew_hash", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMov.put("notificado", new TableInfo.Column("notificado", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final Set<TableInfo.ForeignKey> _foreignKeysMov = new HashSet<TableInfo.ForeignKey>(2);
        _foreignKeysMov.add(new TableInfo.ForeignKey("categoria", "CASCADE", "NO ACTION", Arrays.asList("categoria_id"), Arrays.asList("id")));
        _foreignKeysMov.add(new TableInfo.ForeignKey("mov_recur", "SET NULL", "NO ACTION", Arrays.asList("mov_recur_id"), Arrays.asList("id")));
        final Set<TableInfo.Index> _indicesMov = new HashSet<TableInfo.Index>(2);
        _indicesMov.add(new TableInfo.Index("index_mov_categoria_id", false, Arrays.asList("categoria_id"), Arrays.asList("ASC")));
        _indicesMov.add(new TableInfo.Index("index_mov_mov_recur_id", false, Arrays.asList("mov_recur_id"), Arrays.asList("ASC")));
        final TableInfo _infoMov = new TableInfo("mov", _columnsMov, _foreignKeysMov, _indicesMov);
        final TableInfo _existingMov = TableInfo.read(connection, "mov");
        if (!_infoMov.equals(_existingMov)) {
          return new RoomOpenDelegate.ValidationResult(false, "mov(com.arcaneia.spendwise.data.entity.Mov).\n"
                  + " Expected:\n" + _infoMov + "\n"
                  + " Found:\n" + _existingMov);
        }
        final Map<String, TableInfo.Column> _columnsMovRecur = new HashMap<String, TableInfo.Column>(8);
        _columnsMovRecur.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovRecur.put("nome", new TableInfo.Column("nome", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovRecur.put("importe", new TableInfo.Column("importe", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovRecur.put("periodicidade", new TableInfo.Column("periodicidade", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovRecur.put("data_ini", new TableInfo.Column("data_ini", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovRecur.put("data_rnv", new TableInfo.Column("data_rnv", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovRecur.put("tipo", new TableInfo.Column("tipo", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMovRecur.put("remote_id", new TableInfo.Column("remote_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final Set<TableInfo.ForeignKey> _foreignKeysMovRecur = new HashSet<TableInfo.ForeignKey>(0);
        final Set<TableInfo.Index> _indicesMovRecur = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMovRecur = new TableInfo("mov_recur", _columnsMovRecur, _foreignKeysMovRecur, _indicesMovRecur);
        final TableInfo _existingMovRecur = TableInfo.read(connection, "mov_recur");
        if (!_infoMovRecur.equals(_existingMovRecur)) {
          return new RoomOpenDelegate.ValidationResult(false, "mov_recur(com.arcaneia.spendwise.data.entity.MovRecur).\n"
                  + " Expected:\n" + _infoMovRecur + "\n"
                  + " Found:\n" + _existingMovRecur);
        }
        return new RoomOpenDelegate.ValidationResult(true, null);
      }
    };
    return _openDelegate;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final Map<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final Map<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "categoria", "mov", "mov_recur");
  }

  @Override
  public void clearAllTables() {
    super.performClear(true, "categoria", "mov", "mov_recur");
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final Map<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(CategoriaDao.class, CategoriaDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(MovDao.class, MovDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(MovRecurDao.class, MovRecurDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final Set<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public CategoriaDao categoriaDao() {
    if (_categoriaDao != null) {
      return _categoriaDao;
    } else {
      synchronized(this) {
        if(_categoriaDao == null) {
          _categoriaDao = new CategoriaDao_Impl(this);
        }
        return _categoriaDao;
      }
    }
  }

  @Override
  public MovDao movDao() {
    if (_movDao != null) {
      return _movDao;
    } else {
      synchronized(this) {
        if(_movDao == null) {
          _movDao = new MovDao_Impl(this);
        }
        return _movDao;
      }
    }
  }

  @Override
  public MovRecurDao movRecurDao() {
    if (_movRecurDao != null) {
      return _movRecurDao;
    } else {
      synchronized(this) {
        if(_movRecurDao == null) {
          _movRecurDao = new MovRecurDao_Impl(this);
        }
        return _movRecurDao;
      }
    }
  }
}
