output "resource_group_name" {
  description = "Resource group name"
  value       = module.infrastructure.resource_group_name
}

output "mysql_server_fqdn" {
  description = "MySQL server FQDN"
  value       = module.database.mysql_fqdn
}

output "mysql_server_name" {
  description = "MySQL server name"
  value       = module.database.mysql_server_name
}

output "database_name" {
  description = "Database name"
  value       = module.database.database_name
}

output "vnet_id" {
  description = "VNet ID"
  value       = module.infrastructure.vnet_id
}
