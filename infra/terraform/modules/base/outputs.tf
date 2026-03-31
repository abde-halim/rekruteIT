output "resource_group_id" {
  description = "Resource group ID"
  value       = azurerm_resource_group.main.id
}

output "resource_group_name" {
  description = "Resource group name"
  value       = azurerm_resource_group.main.name
}

output "vnet_id" {
  description = "Virtual network ID"
  value       = azurerm_virtual_network.main.id
}

output "subnet_id" {
  description = "Subnet ID"
  value       = azurerm_subnet.internal.id
}

output "nic_id" {
  description = "Network interface ID"
  value       = azurerm_network_interface.main.id
}

output "location" {
  description = "Location of resources"
  value       = azurerm_resource_group.main.location
}
