package org.m2cs.mmislamicbooks.delegates

import org.m2cs.mmislamicbooks.data.vo.BookVO

interface BookMainDelegate{
    fun onTapBook(bookVO: BookVO)
}
