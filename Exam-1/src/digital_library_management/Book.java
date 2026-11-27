package digital_library_management;

import java.util.Objects;

public class Book implements Searchable {

        private String isbn;
        private String title;
        private String author;
        private int borrowCount;

        public Book(String isbn, String title, String author, int borrowCount) {
            this.isbn = isbn;
            this.title = title;
            this.author = author;
            this.borrowCount = borrowCount;
        }

        public String getIsbn() {
            return isbn;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        public int getBorrowCount() {
            return borrowCount;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (!(obj instanceof Book))
                return false;
            Book other = (Book) obj;
            return isbn.equals(other.isbn);
        }

        @Override
        public int hashCode() {
            return Objects.hash(isbn);
        }

        @Override
        public String toString() {
            return "Book [isbn=" + isbn +
                    ", title=" + title +
                    ", author=" + author +
                    ", borrowCount=" + borrowCount + "]";
        }

        @Override
        public boolean matchesKeyword(String keyword) {
            keyword = keyword.toLowerCase();
            return title.toLowerCase().contains(keyword) || author.toLowerCase().contains(keyword);
        }

}
