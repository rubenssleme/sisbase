package br.laramara.ti.sislaraserver.repositorios.integracao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class EnumOrderTypeHandler<E extends Enum<E>> extends BaseTypeHandler<E> {

	private E[] constants;

	protected EnumOrderTypeHandler(Class<E> javaType) {
		constants = javaType.getEnumConstants();
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, E parameter,
			JdbcType jdbcType) throws SQLException {
		ps.setInt(i, parameter.ordinal() + 1);
	}

	@Override
	public E getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
		int index = rs.getInt(columnName) - 1;
		return index < 0 ? null : constants[index];
	}

	@Override
	public E getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException {
		int index = rs.getInt(rs.getInt(columnIndex)) - 1;
		return index < 0 ? null : constants[index];
	}

	@Override
	public E getNullableResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		int index = cs.getInt(columnIndex) - 1;
		return index < 0 ? null : constants[index];
	}
}
