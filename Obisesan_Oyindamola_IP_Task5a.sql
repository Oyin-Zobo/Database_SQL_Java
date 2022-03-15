--1
insert into customer 
values 
('Oyindamola', '2900 Beaumont', 2),

--2
insert into department 
values 
(7, 'this is Mewbourne');

--3
insert into department
values 
(9, 'DSA'); 

insert into processes 
values
('40', 9, 'process?')

update processes_fit
set fit_type = 'straight'
where pid = '40'

--4
insert into assemblies 
values 
('78', '67', 'Kome', '23-05-2021', 'assembly')

insert into orders 
values 
('Kome', '78')

insert into has 
values 
('78', '67')

--5
insert into acct_assem
values 
(2333455, '6', '2021-04-04', 567)

--6

insert into job_fit(job_id, date_start)
values 
(56, '2021-04-05');

insert into done 
values 
(34, '45', 56)

insert has 
values 
('67', '56')

--7
update job_fit
set 
    date_end = '2021-04-06', 
    labor_time_fit = 5
where job_id = 56


--8 
insert into transactions
values 
(345, 45)

--9
select sum (cost_assm)
from acct_assem
where assm_id = 34

--10
select sum (total_labor) from (   
    select sum(labor_time_cut) as total_labor from job_cut where job_id in (select job_id from done where dept_num = 2) and date_end = '2021-04-03' union all 
    select sum(labor_time_cut) as total_labor from job_fit where job_id in (select job_id from done where dept_num = 2) and date_end = '2021-04-03' union all 
    select sum(labor_time_cut) as total_labor from job_paint where job_id in (select job_id from done where dept_num = 2) and date_end = '2021-04-03'
) total_labor_time
--x group by dep_num 

--11
select done.job_id, dept_num
from assemblies 

inner join 
done on assemblies.pid = done.pid
right join
    (select job_id, date_start, date_end 
    from job_cut 
    UNION
    select job_id, date_start, date_end
    from job_paint 
    UNION
    select job_id, date_start, date_end
    from job_fit) aa 
    on done.job_id = aa.job_id
    where assm_id = 45
order by date_start 

--12a 
select *
from assemblies 
inner join 
done on assemblies.pid = done.pid 
right join
job_cut on done.job_id = job_cut.job_id

--12b 
select *
from assemblies 
inner join 
done on assemblies.pid = done.pid 
right join
job_fit on done.job_id = job_fit.job_id

--12c
select *
from assemblies 
inner join 
done on assemblies.pid = done.pid 
right join
job_paint on done.job_id = job_paint.job_id

--13
select cust_name 
from customer
where category between 4 and 5
order by cust_name asc 

-- 14
delete 
from job_cut
where job_id between 40 and 60 

--15 
update job_paint
set color = 'red'
where job_id = 56



--TSQL

drop procedure query1
go
create PROCEDURE query1
    @cust_name varchar(30),
    @addr varchar(30),
    @category integer
    
    as 
    BEGIN
        insert into customer (cust_name, addr, category)
        values (@cust_name, @addr, @category)

    end

drop procedure query2

go 
create PROCEDURE query2
    @dept_num integer, 
    @dep_data varchar(30)

as 
BEGIN
    insert into department (dept_num, dep_data)
    values (@dept_num, @dep_data)

end 

drop procedure query3
go 
create procedure query3
    @pid varchar(12), 
    @process_data varchar(30), 
    @dept_num integer,
    @type_process varchar(30),
    @cut_type varchar(30),
    @machine_type VARCHAR(30),
    @fit_type VARCHAR(30),
    @paint_type varchar(30),
    @paint_method varchar(30)
as 
BEGIN
    --insert department (dept_num)
    --values (@dept_num)

    INSERT processes (pid, process_data, dept_num)
    values (@pid, @process_data, @dept_num) 

if @type_process = 'cut'
    insert into processes_cut (pid, cut_type, machine_type, dept_num)
    values (@pid, @cut_type, @machine_type, @dept_num)
if @type_process = 'fit'
    insert into processes_fit (pid, fit_type, dept_num)
    values (@pid, @fit_type, @dept_num)
if @type_process = 'paint'
    insert into processes_paint (pid, paint_type, paint_method, dept_num)
    values (@pid, @paint_type, @paint_method, @dept_num)
end

drop PROCEDURE query4

go 
CREATE procedure query4
    @assm_id varchar(12),
    @pid integer, 
    @cust_name varchar(30),
    @dat_assm date,
    @detail varchar(30), 
    @dep_num INTEGER, 
    @job_id integer

as 
begin 

    --insert customer (cust_name)
    --values (@cust_name)

    insert into assemblies (assm_id, pid, cust_name, dat_assm, detail)
    values (@assm_id, @pid, @cust_name, @dat_assm, @detail)

    insert orders (assm_id, cust_name)
    values (@assm_id, @cust_name)

    insert into has (assm_id, pid)
    values (@assm_id, @pid)

end 

drop procedure query5

go 
create procedure query5
    @acct_num integer,
    @pid varchar(12),
    @assm_id varchar(12),
    @date_est date, 
    @dept_num integer, 
    @type_acct varchar(12),
    @cost_assm numeric, 
    @cost_dept numeric, 
    @cost_pro numeric
as 
begin 
if (@type_acct = 'assembly')
    begin
        insert into acct_assem (acct_num, assm_id, date_est, cost_assm)
        values (@acct_num, @assm_id, @date_est, @cost_assm)
    end 
if (@type_acct = 'department')
    begin
        insert into acct_dep (acct_num, dept_num, date_est, cost_dept)
        values (@acct_num, @dept_num, @date_est, @cost_dept)
    end 

if (@type_acct = 'process')
    begin
        insert into acct_pro (pid, acct_num, date_est, cost_pro)
        values (@pid, @acct_num, @date_est, @cost_pro)
    end 

end

drop procedure query6

GO
create procedure query6
    @job_id INTEGER,
    @date_start date,
    @dept_num integer, 
    @pid varchar(12),
    @assm_id varchar(12),
    @type_job varchar (12)

as 
begin 
    --INSERT into job_fit (job_id, date_start)
    --values (@job_id, @date_start)

    --insert into department(dept_num)
    --values (@dept_num)
    insert into done (dep_num, pid, job_id)
    values (@dept_num, @pid, @job_id)

    insert into has (assm_id, pid)
    values (@assm_id, @pid)

if @type_job = 'fit'
    INSERT into job_fit (job_id, date_start)
    values (@job_id, @date_start)
if @type_job = 'cut'
    insert into job_cut (job_id, date_start)
    values (@job_id, @date_start)
if @type_job = 'paint'
    insert into job_paint (job_id, date_start)
    values (@job_id, @date_start)

 

end 

drop procedure query7

go 
create procedure query7
    @date_end date, 
    @labor_time_cut numeric,
    @labor_time_fit numeric, 
    @labor_time_paint numeric,
    @job_id integer,
    @type_job varchar(12), 
    @type_of_mac varchar(15),
    @amt_time numeric,
    @mat_used varchar(30),
    @volume numeric, 
    @color varchar(12)

as

BEGIN

begin 
    update job_cut
    set 
        date_end = @date_end, 
        labor_time_cut = @labor_time_cut,
        type_of_mac = @type_of_mac,
        amt_time = @amt_time,
        mat_used = @mat_used
    where job_id = @job_id and @type_job = 'cut'

end 

begin
    update job_fit
    set 
        date_end = @date_end, 
        labor_time_fit = @labor_time_fit
    where job_id = @job_id and @type_job = 'fit'
end 

begin
    update job_paint
    set 
        date_end = @date_end, 
        labor_time_paint = @labor_time_paint,
        volume = @volume,
        color = @color 
    where job_id = @job_id and @type_job = 'paint'

end 

END

drop procedure query8

go 
create procedure query8
    @trans_id varchar(15),
    @sub_cost numeric,
    @acct_type varchar(15), 
    @assm_id varchar(15),
    @acct_num integer,
    @date_est date

as 
begin 
    insert into transactions (trans_id, sub_cost)
    values (@trans_id, @sub_cost)
end 

begin
update acct_assem 
set 
    cost_assm = cost_assm + @sub_cost,
    date_est = @date_est
where acct_num = @acct_num and @acct_type = 'assembly'
end 

begin
update acct_dep
set 
    cost_dept = cost_dept + @sub_cost,
    date_est = @date_est
where acct_num = @acct_num and @acct_type = 'department'
end 

begin
update acct_pro 
set 
    cost_pro = cost_pro + @sub_cost,
    date_est = @date_est
where acct_num = @acct_num and @acct_type = 'assembly'
end 


drop PROCEDURE query9
go 
create procedure query9
    @assm_id varchar (12)
as 
begin
    select sum (cost_assm) as total_cost
    from acct_assem
    where assm_id = @assm_id
end 

drop procedure query10
GO
create procedure query10
    @dept_num integer, 
    @date_end date
as 
begin
select sum (total_labor) from (   
    select sum(labor_time_cut) as total_labor from job_cut where job_id in (select job_id from done where dep_num = @dept_num) and date_end = @date_end union all 
    select sum(labor_time_fit) as total_labor from job_fit where job_id in (select job_id from done where dep_num = @dept_num) and date_end = @date_end union all 
    select sum(labor_time_paint) as total_labor from job_paint where job_id in (select job_id from done where dep_num = @dept_num) and date_end = @date_end
) total_labor_time

end 

drop procedure query11

GO
create procedure query11
    @assm_id varchar(12)
as 
begin 
    select done.job_id, dep_num
    from assemblies 
    inner join 
    done on assemblies.pid = done.pid
    right join
        (select job_id, date_start, date_end 
        from job_cut 
        UNION
        select job_id, date_start, date_end
        from job_paint 
        UNION
        select job_id, date_start, date_end
        from job_fit) aa 
        on done.job_id = aa.job_id
        where assm_id = @assm_id
    order by date_start 

end 

drop procedure query12

GO
create PROCEDURE query12
    @job_type varchar(12),
    @date_end date, 
    @dept_num integer

as 
begin 
if @job_type = 'cut'
    select *
    from assemblies 
    inner join 
    done on assemblies.pid = done.pid 
    right join
    job_cut on done.job_id = job_cut.job_id
    where date_end = @date_end and dep_num = @dept_num 

if @job_type = 'fit'
    select *
    from assemblies 
    inner join 
    done on assemblies.pid = done.pid 
    right join
    job_fit on done.job_id = job_fit.job_id
    where date_end = @date_end and dep_num = @dept_num 

if @job_type = 'paint'
    select *
    from assemblies 
    inner join 
    done on assemblies.pid = done.pid 
    right join
    job_paint on done.job_id = job_paint.job_id
    where date_end = @date_end and dep_num = @dept_num 
end


go 
create procedure query13
    @cat_1 integer, 
    @cat_2 integer

as 
BEGIN   
    select cust_name 
    from customer
    where category between @cat_1 and @cat_2
    order by cust_name asc 
end 

go 
create procedure query14
    @job_id_1 integer, 
    @job_id_2 INTEGER
as
begin
    delete 
    from job_cut
    where job_id between @job_id_1 and @job_id_2
end  

go 
create procedure query15
    @job_id integer,
    @color varchar(15)
as 
begin 
    update job_paint
    set color = @color
    where job_id = @job_id

end 

drop procedure query16

go 
create procedure query16
    @cust_name varchar (30),
    @addr varchar(30),
    @category integer
as 
begin 
    insert into customer
    values(@cust_name, @addr, @category)
end

drop procedure query17

go 
create procedure query17
    @cat_1 integer, 
    @cat_2 integer 
as 
begin 
    select cust_name 
    from customer 
    where category between @cat_1 and @cat_2 
order by cust_name asc
end 
