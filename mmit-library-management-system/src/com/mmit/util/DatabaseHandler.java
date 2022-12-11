package com.mmit.util;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.mmit.model.Author;
import com.mmit.model.Book;
import com.mmit.model.Category;
import com.mmit.model.Librarian;

public class DatabaseHandler {

	// start create connection
	private static Connection createConnection() throws SQLException {
		String username = "root";
		String password = "root";

		Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/lms", username, password);
		return con;
	}
	// end create connection

	// start librarian section
	public static Librarian librarianLogin(String email, String password) throws Exception {
		Librarian librarian = null;

		try (Connection con = createConnection()) {
			var query = "SELECT * FROM librarians WHERE email = ? AND password = ?";

			PreparedStatement pstm = con.prepareStatement(query);

			pstm.setString(1, email);
			pstm.setString(2, password);

			ResultSet result = pstm.executeQuery();

			if (result.next()) {
				librarian = new Librarian();
				librarian.setId(result.getInt("id"));
				librarian.setEmail(email);
				librarian.setPassword(password);
			}
		} catch (Exception e) {
			throw e;
		}
		return librarian;
	}

	public static void librarianRegister(Librarian librarian) throws Exception {

		try (Connection con = createConnection()) {
			var query = "INSERT INTO librarians(email, password, nrcno, phone)VALUES(?, ?, ?, ?)";

			PreparedStatement pstm = con.prepareStatement(query);

			pstm.setString(1, librarian.getEmail());
			pstm.setString(2, librarian.getPassword());
			pstm.setString(3, librarian.getNrcno());
			pstm.setString(4, librarian.getPhone());

			pstm.executeQuery();

		} catch (Exception e) {
			throw e;
		}
	}

	public static List<Librarian> showAllLibrarian() throws Exception {
		List<Librarian> list = new ArrayList<>();
		try (Connection con = createConnection()) {
			var query = "SELECT * FROM librarians";

			var pstm = con.prepareStatement(query);
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				Librarian librarian = new Librarian();
				librarian.setId(rs.getInt("id"));
				librarian.setEmail(rs.getString("email"));
				librarian.setPassword(rs.getString("password"));
				librarian.setNrcno(rs.getString("nrcno"));
				librarian.setPhone(rs.getString("phone"));

				list.add(librarian);
			}
		} catch (Exception e) {
			throw e;
		}
		return list;
	}

	public static void editLibrarian(Librarian selected_librarian) throws Exception {
		try (Connection con = createConnection()) {
			var query = "UPDATE librarians SET email = ?, password = ?, nrcno = ?, phone = ? WHERE id = ?";
			var pstm = con.prepareStatement(query);
			pstm.setString(1, selected_librarian.getEmail());
			pstm.setString(2, selected_librarian.getPassword());
			pstm.setString(3, selected_librarian.getNrcno());
			pstm.setString(4, selected_librarian.getPhone());
			pstm.setInt(5, selected_librarian.getId());

			pstm.executeUpdate();

		} catch (Exception e) {
			throw e;
		}
	}

	public static void deleteLibrarian(int id) throws Exception {
		try (Connection con = createConnection()) {
			var query = "DELETE FROM librarians WHERE id = ?";
			var pstm = con.prepareStatement(query);
			pstm.setInt(1, id);

			pstm.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
	}

	public static Librarian searchLibrarian(int id) throws Exception {
		Librarian librarian = null;
		try (Connection con = createConnection()) {
			var query = "SELECT * FROM librarians WHERE librarians.id = ?";
			var pstm = con.prepareStatement(query);
			pstm.setInt(1, id);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				librarian = new Librarian();
				librarian.setEmail(rs.getString("email"));
				librarian.setPassword(rs.getString("password"));
				librarian.setNrcno(rs.getString("nrcno"));
				librarian.setPhone(rs.getString("phone"));
			}

		} catch (Exception e) {
			throw e;
		}
		return librarian;
	}
	// end librarian section

	// start author section
	public static void addAuthor(Author author) throws Exception {
		try (Connection con = createConnection()) {
			var query = "INSERT INTO authors(name, city, birthday)VALUES(?, ?, ?)";
			var pstm = con.prepareStatement(query);
			pstm.setString(1, author.getName());
			pstm.setString(2, author.getCity());
			pstm.setDate(3, Date.valueOf(author.getBirthday()));

			pstm.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
	}

	public static List<Author> showAllAuthor() throws Exception {
		List<Author> author_list = new ArrayList<>();
		try (Connection con = createConnection()) {
			var query = "SELECT * FROM authors";
			var pstm = con.prepareStatement(query);
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				Author author = new Author();
				author.setId(rs.getInt("id"));
				author.setName(rs.getString("name"));
				author.setBirthday(LocalDate.parse(rs.getString("birthday")));
				author.setCity(rs.getString("city"));

				author_list.add(author);
			}
		} catch (Exception e) {
			throw e;
		}
		return author_list;
	}

	public static void editAuthor(Author author) throws Exception {
		try (Connection con = createConnection()) {
			var query = "UPDATE authors SET name = ?, city = ?, birthday = ? WHERE id = ?";
			var pstm = con.prepareStatement(query);
			pstm.setString(1, author.getName());
			pstm.setString(2, author.getCity());
			pstm.setDate(3, Date.valueOf(author.getBirthday()));
			pstm.setInt(4, author.getId());

			pstm.executeUpdate();

		} catch (Exception e) {
			throw e;
		}
	}

	public static void deleteAuthor(int id) throws Exception {
		try (Connection con = createConnection()) {
			var query = "DELETE FROM authors WHERE id = ?";
			var pstm = con.prepareStatement(query);
			pstm.setInt(1, id);

			pstm.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
	}

	public static List<Category> showAllCategory() throws Exception {

		List<Category> category_list = new ArrayList<>();
		try (Connection con = createConnection()) {
			var query = "SELECT * FROM categories";
			var pstm = con.prepareStatement(query);
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				Category category = new Category();
				category.setId(rs.getInt("id"));
				category.setName(rs.getString("name"));

				category_list.add(category);
			}
		} catch (Exception e) {
			throw e;
		}
		return category_list;
	}
	// end author section

	// start category section
	public static void addCategory(Category category) throws Exception {
		try (Connection con = createConnection()) {
			var query = "INSERT INTO categories(name)VALUES(?)";
			var pstm = con.prepareStatement(query);
			pstm.setString(1, category.getName());

			pstm.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
	}

	public static void deleteCategory(int id) throws Exception {
		try (Connection con = createConnection()) {
			var query = "DELETE FROM categories WHERE id = ?";
			var pstm = con.prepareStatement(query);
			pstm.setInt(1, id);

			pstm.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
	}

	public static void editCategory(Category category) throws Exception {
		try (Connection con = createConnection()) {
			var query = "UPDATE categories SET name = ? WHERE id = ?";
			var pstm = con.prepareStatement(query);
			pstm.setString(1, category.getName());
			pstm.setInt(2, category.getId());

			pstm.executeUpdate();

		} catch (Exception e) {
			throw e;
		}

	}
	// end category section

	// start book section
	public static void addBook(Book book) throws Exception {
		try (Connection con = createConnection()) {
			var query = "INSERT INTO books(code, title, publish_date, author_id, category_id, created_by, available)VALUES(?, ?, ?, ?, ?, ?, ?)";
			var pstm = con.prepareStatement(query);

			pstm.setInt(1, book.getCode());
			pstm.setString(2, book.getTitle());
			pstm.setDate(3, Date.valueOf(book.getPublish_date()));
			pstm.setInt(4, book.getAuthor().getId());
			pstm.setInt(5, book.getCategory().getId());
			pstm.setInt(6, book.getLibrarian().getId());
			pstm.setString(7, book.getAvailable());

			pstm.executeUpdate();

		} catch (Exception e) {
			throw e;
		}
	}

	public static List<Book> showAllBook() throws Exception {
		List<Book> book_list = new ArrayList<>();

		try (Connection con = createConnection()) {
			var query = """
					SELECT books.*, authors.name 'author_name', categories.name 'category_name', librarians.email
					FROM books, authors, categories, librarians
					WHERE books.author_id = authors.id && books.category_id = categories.id && books.created_by = librarians.id
					""";
			var pstm = con.prepareStatement(query);

			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Author author = new Author();
				author.setId(rs.getInt("author_id"));
				author.setName(rs.getString("author_name"));

				Category category = new Category();
				category.setId(rs.getInt("category_id"));
				category.setName(rs.getString("category_name"));

				Librarian librarian = new Librarian();
				librarian.setId(rs.getInt("created_by"));
				librarian.setEmail(rs.getString("email"));

				Book book = new Book();
				book.setCode(rs.getInt("code"));
				book.setTitle(rs.getString("title"));
				book.setPublish_date(LocalDate.parse(rs.getString("publish_date")));
				book.setAvailable(rs.getString("available"));
				book.setAuthor(author);
				book.setCategory(category);
				book.setLibrarian(librarian);

				book_list.add(book);
			}
		} catch (Exception e) {
			throw e;
		}
		return book_list;
	}

	public static List<Book> searchBook(String title, String author, String category) throws Exception {
		List<Book> book_list = new ArrayList<>();

		try (Connection con = createConnection()) {
			var query = """
					SELECT books.*, authors.name 'author_name', categories.name 'category_name', librarians.email
					FROM books, authors, categories, librarians
					WHERE books.author_id = authors.id && books.category_id = categories.id && books.created_by = librarians.id
					""";
			List<String> input_search = new ArrayList<>();
			if (!title.isEmpty()) {
				query += " AND books.title LIKE ?";
				input_search.add("%" + title + "%");
			}
			if (!author.isEmpty()) {
				query += " AND authors.name LIKE ?";
				input_search.add("%" + author + "%");
			}
			if (!category.isEmpty()) {
				query += " AND categories.name LIKE ?";
				input_search.add("%" + category + "%");
			}

			var pstm = con.prepareStatement(query);
			for (int i = 0; i < input_search.size(); i++) {
				pstm.setObject((i + 1), input_search.get(i));
			}

			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Author auth = new Author();
				auth.setId(rs.getInt("author_id"));
				auth.setName(rs.getString("author_name"));

				Category cat = new Category();
				cat.setId(rs.getInt("category_id"));
				cat.setName(rs.getString("category_name"));

				Librarian librarian = new Librarian();
				librarian.setId(rs.getInt("created_by"));
				librarian.setEmail(rs.getString("email"));

				Book book = new Book();
				book.setCode(rs.getInt("code"));
				book.setTitle(rs.getString("title"));
				book.setPublish_date(LocalDate.parse(rs.getString("publish_date")));
				book.setAvailable(rs.getString("available"));
				book.setAuthor(auth);
				book.setCategory(cat);
				book.setLibrarian(librarian);

				book_list.add(book);
			}
		} catch (Exception e) {
			throw e;
		}
		return book_list;
	}

	public static Book searchBookByCode(int code) throws Exception {
		Book book = null;
		try (Connection con = createConnection()) {
			var query = """
					SELECT books.*, authors.name 'author_name', categories.name 'category_name', librarians.email
					FROM books, authors, categories, librarians
					WHERE books.author_id = authors.id && books.category_id = categories.id && books.created_by = librarians.id && books.code = ?
					""";
			var pstm = con.prepareStatement(query);
			pstm.setInt(1, code);
			ResultSet rs = pstm.executeQuery();

			if (rs.next()) {
				Author auth = new Author();
				auth.setId(rs.getInt("author_id"));
				auth.setName(rs.getString("author_name"));

				Category cat = new Category();
				cat.setId(rs.getInt("category_id"));
				cat.setName(rs.getString("category_name"));

				Librarian librarian = new Librarian();
				librarian.setId(rs.getInt("created_by"));
				librarian.setEmail(rs.getString("email"));

				book = new Book();
				book.setCode(rs.getInt("code"));
				book.setTitle(rs.getString("title"));
				book.setPublish_date(LocalDate.parse(rs.getString("publish_date")));
				book.setAvailable(rs.getString("available"));
				book.setAuthor(auth);
				book.setCategory(cat);
				book.setLibrarian(librarian);

			}
		} catch (Exception e) {
			throw e;
		}
		return book;
	}

	public static void editBook(Book book) throws Exception {
		try (Connection con = createConnection()) {
			var query = """
					UPDATE books
					SET title = ?, publish_date = ?, author_id = ?, category_id = ?
					WHERE code = ?
					""";
			var pstm = con.prepareStatement(query);
			pstm.setString(1, book.getTitle());
			pstm.setDate(2, Date.valueOf(book.getPublish_date()));
			pstm.setInt(3, book.getAuthor().getId());
			pstm.setInt(4, book.getCategory().getId());
			pstm.setInt(5, book.getCode());

			pstm.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
	}

	public static void deleteBook(int code) throws Exception {
		try (Connection con = createConnection()) {
			var query = "DELETE FROM books WHERE code = ?";
			var pstm = con.prepareStatement(query);

			pstm.setInt(1, code);
			pstm.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	// end book section

	// start category section
	// end category section

	// start category section
	// end category section

	// start category section
	// end category section

}
