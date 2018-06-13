package org.m2cs.mmislamicbooks.events

import org.m2cs.mmislamicbooks.data.vo.BookVO

class DataEvents {
    class BookLoadedEvent(var bookList: List<BookVO>)
}
