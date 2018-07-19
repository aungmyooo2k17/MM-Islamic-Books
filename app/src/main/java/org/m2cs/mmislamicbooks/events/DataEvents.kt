package org.m2cs.mmislamicbooks.events

import org.m2cs.mmislamicbooks.data.vo.BookVO
import org.m2cs.mmislamicbooks.data.vo.CategoryVO

class DataEvents {
    class BookLoadedEvent(var bookList: List<BookVO>)

    class CategoryLoadedEvent(var categoryList: List<CategoryVO>)
}
