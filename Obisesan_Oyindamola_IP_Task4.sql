drop table if exists records
drop table if exists orders
drop table if exists processes 
drop table if exists processes_fit
drop table if exists processes_paint
drop table if exists processes_cut
drop table if exists has 
drop table if exists job_cut
drop table if exists job_paint
drop table if exists job_fit
drop table if exists acct_assem
drop table if exists acct_dep
drop table if exists acct_pro
drop table if exists updates
drop table if exists records
drop table if exists transactions
drop table if exists done  
drop table if exists department 
drop table if exists assemblies 
drop table if exists customer
 

create table customer(
	cust_name varchar(30) primary key,
	addr varchar(30),
	category integer
	);

create table assemblies(
	assm_id varchar(12), 
    pid varchar(12),
    cust_name varchar(30) foreign key references customer (cust_name),
	dat_assm date, 
	detail varchar(40),
    primary key (assm_id, pid, cust_name)
	); 

create table orders(
	--assm_id varchar(12) foreign key references assemblies (assm_id), 
	assm_id varchar(12),
    cust_name varchar(30) foreign key references customer (cust_name),
	primary key (cust_name, assm_id)
	);

create table has(
	--assm_id varchar(12) foreign key references assemblies (assm_id), 
    assm_id varchar(12),
    pid varchar(12), 
	--job_id integer, 
	primary key (assm_id, pid)
);

create table department(
	dept_num integer primary key, 
	dep_data varchar(30)
	); 

create table processes(
	pid varchar(12), 
	process_data varchar(30),
	--assm_id varchar(12) foreign key references assemblies(assm_id), 
	--assm_id varchar(12),
    dept_num integer foreign key references department(dept_num), 
	primary key (pid, dept_num)
	);

create table processes_fit(
	pid varchar(12), 
	fit_type varchar(12),
	--assm_id varchar(12) foreign key references assemblies(assm_id), 
	--assm_id varchar(12),
    dept_num integer foreign key references department(dept_num), 
	primary key (pid, dept_num)
	);

create table processes_paint(
	pid varchar(12), 
	paint_type varchar(12),
	paint_method varchar(20),
	--assm_id varchar(12) foreign key references assemblies(assm_id), 
	--assm_id varchar(12),
    dept_num integer foreign key references department(dept_num), 
	primary key (pid, dept_num)
	);

create table processes_cut(
	pid varchar(12), 
	cut_type varchar(12),
	machine_type varchar (12),
	--assm_id varchar(12) foreign key references assemblies(assm_id), 
	--assm_id varchar(12),
    dept_num integer foreign key references department(dept_num), 
	primary key (pid, dept_num)
	);

create table job_cut(
	job_id integer primary key, 
	type_of_mac varchar(15), 
	amt_time numeric, 
	mat_used varchar(30), 
	labor_time_cut numeric, 
	date_start date, 
	date_end date
);

create table job_paint(
	job_id integer primary key, 
	color varchar(12), 
	volume numeric, 
	labor_time_paint numeric, 
	date_start date, 
	date_end date
);

create table job_fit(
	job_id integer primary key, 
	labor_time_fit numeric, 
	date_start date, 
	date_end date
);

create table acct_assem(
	acct_num integer, 
	--assm_id varchar(12) foreign key references assemblies (assm_id), 
	assm_id varchar(12),
    date_est date, 
	cost_assm numeric, 
	primary key(acct_num, assm_id)
	); 

create table acct_dep(
	acct_num integer, 
	dept_num integer foreign key references department (dept_num), 
	date_est date, 
	cost_dept numeric
	primary key (acct_num, dept_num)
	); 

create table acct_pro(
	pid varchar(12), 
	acct_num integer, 
	date_est date, 
	cost_pro numeric, 
	primary key (acct_num, pid)
	); 

create table transactions(
	trans_id varchar(15) primary key, 
	sub_cost numeric
	);

create table updates(
	acct_num integer, 
	trans_id varchar(15) foreign key references transactions(trans_id),
	primary key (acct_num, trans_id)
); 

create table records(
	trans_id varchar(15) foreign key references transactions(trans_id),
	job_id integer, 
	primary key (trans_id, job_id)
);

create table done(
	--pid varchar(12) foreign key references processes(pid), 
	dep_num integer foreign key references department (dept_num),
    pid varchar(12),
	job_id integer
	primary key (job_id,pid, dep_num)
);