select concat('insert into staff (staff_name,staff_job_name,staff_cell,staff_domain,staff_group_name,staff_department_name,staff_qq,staff_gender,staff_grade,staff_campus,staff_oapwd,staff_wechatacc,staff_wechatopenid,staff_addrbuilding,staff_addrroom,staff_job_id,staff_group_id,staff_department_id,staff_group_role_id,staff_group_role_name,staff_stuid) values (\''
 ,staff_name,'\',\'',staff_job_name,'\',\'',staff_cell,'\',(select su.staff_domain from staff_unchecked su where staff_stuid=\'',staff_stuid,'\'),\'',staff_group_name,'\',\'',staff_department_name,'\',\'',staff_qq,'\',',staff_gender,',',staff_grade,',\'',staff_campus,'\',\'',staff_oapwd,'\',\'',staff_wechatacc,'\',\'',staff_wechatopenid,'\',\'',staff_addrbuilding,'\',\'',staff_addrroom,'\',',staff_job_id,',',staff_group_id,',',staff_department_id,',',staff_group_role_id,',\'',staff_group_role_name,'\',\'',staff_stuid,'\');') as `sql`
 from staff_unchecked