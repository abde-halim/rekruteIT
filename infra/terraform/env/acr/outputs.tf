output "resource_group_name" {
  description = "Resource group name"
  value       = module.infrastructure.resource_group_name
}

output "acr_login_server" {
  description = "ACR login server URL"
  value       = module.container_registry.acr_login_server
}

output "acr_id" {
  description = "ACR ID"
  value       = module.container_registry.acr_id
}

output "vnet_id" {
  description = "VNet ID"
  value       = module.infrastructure.vnet_id
}
