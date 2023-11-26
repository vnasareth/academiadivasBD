package edu.poo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PagamentoDAOImpl implements IAcademiaDAO<Pagamento> {

	private final static String JDBC_CLASS = "org.mariadb.jdbc.Driver";
    private static final String JDBC_URL = "jdbc:mariadb://localhost:3306/academiadb?allowMultiQueries=true";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASS = "";
    private Connection con;

    public PagamentoDAOImpl() {
        try {
            Class.forName(JDBC_CLASS);
            con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
	
	@Override
	public void inserir(Pagamento pagamento) {
		String sql = "INSERT INTO pagamento (id, id_aluno, data, valor, tipo, info) ";
		sql += " VALUES (0, ?, ?, ?, ?, ?) ";
		try {
            PreparedStatement stmt = con.prepareStatement(sql);
            Aluno aluno = pagamento.getAluno();
            stmt.setInt(1, aluno.getId());
            stmt.setDate(2, java.sql.Date.valueOf(pagamento.getDataPagamento()));
            stmt.setDouble(3, pagamento.getValorPagamento());
            stmt.setString(4, pagamento.getTipoPagamento());
            stmt.setString(5, pagamento.getInfoAdicional());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
	}

	@Override
	public List<Pagamento> consultar(String nome) {
		IAcademiaDAO<Aluno> dao = new AlunosDAOImpl();
		List<Pagamento> lista = new ArrayList<>();
        String sql = "SELECT p.id, a.nome, p.data, p.valor, p.tipo, p.info FROM pagamento p, aluno a "
        		+ " WHERE p.id_aluno = a.id AND a.nome LIKE '%" + nome + "%'";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
            	Pagamento pagamento = new Pagamento();
                pagamento.setId(rs.getInt("id"));
                pagamento.setDataPagamento(rs.getDate("data").toLocalDate());
                pagamento.setValorPagamento(rs.getDouble("valor"));
                pagamento.setTipoPagamento(rs.getString("tipo"));
                pagamento.setInfoAdicional(rs.getString("info"));
                Aluno aluno = ((AlunosDAOImpl) dao).consultarAluno(rs.getString("nome"));
                pagamento.setAluno(aluno);
                lista.add(pagamento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
	}
	

}
