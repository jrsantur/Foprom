package pe.juabsa.cipca.foprom.vo

data  class Job (
        val company_name:String?,
        val company_address: String?,
        val position_company: String?,
        val company_work_time: Int?,
        val type_company: Int?,
        val salary_frequency: Int?,
        val biweekly_salary: Float,
        val monthly_salary: Float
)