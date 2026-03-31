variable "project_name" {
  description = "Project name"
  type        = string
  default     = "rekruite"
}

variable "resource_group_name" {
  description = "Resource group name"
  type        = string
}

variable "location" {
  description = "Azure region"
  type        = string
  default     = "switzerlandnorth"
}

variable "vnet_address_space" {
  description = "VNet address space"
  type        = list(string)
  default     = ["10.0.0.0/16"]
}

variable "subnet_address_prefixes" {
  description = "Subnet address prefixes"
  type        = list(string)
  default     = ["10.0.2.0/24"]
}

variable "db_server_name" {
  description = "MySQL server name"
  type        = string
}

variable "db_admin_login" {
  description = "Database admin username"
  type        = string
  sensitive   = true
}

variable "db_admin_password" {
  description = "Database admin password"
  type        = string
  sensitive   = true
}

variable "db_sku_name" {
  description = "Database SKU"
  type        = string
  default     = "B_Standard_B1ms"
}

variable "db_storage_size_gb" {
  description = "Database storage size in GB"
  type        = number
  default     = 20
}

variable "db_backup_retention_days" {
  description = "Backup retention days"
  type        = number
  default     = 7
}

variable "db_geo_redundant_backup" {
  description = "Enable geo-redundant backups"
  type        = bool
  default     = false
}

variable "db_high_availability" {
  description = "Enable high availability"
  type        = bool
  default     = false
}

variable "db_zone_redundant_ha" {
  description = "Enable zone-redundant HA"
  type        = bool
  default     = false
}

variable "db_name" {
  description = "Database name"
  type        = string
  default     = "rekruitedb"
}

variable "tags" {
  description = "Resource tags"
  type        = map(string)
  default = {
    environment = "production"
    project     = "rekruite"
  }
}
