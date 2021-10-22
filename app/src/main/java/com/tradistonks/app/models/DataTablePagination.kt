package com.tradistonks.app.models

data class DataTablePagination(
    val page: Int,
    val rowsPerPage: Int,
    val onPageChange: (Int) -> Unit,
    val availableRowsPerPage: List<Int>,
    val onRowsPerPageChange: (Int) -> Unit
)