variable "server_name" {
  description = "MySQL flexible server name"
  type        = string
}

variable "resource_group_name" {
  description = "Name of the resource group"
  type        = string
}

variable "location" {
  description = "Azure region for resources"
  type        = string
}

variable "administrator_login" {
  description = "Administrator username"
  type        = string
  sensitive   = true
}

variable "administrator_password" {
  description = "Administrator password"
  type        = string
  sensitive   = true
}

variable "sku_name" {
  description = "SKU for MySQL server"
  type        = string
  default     = "B_Standard_B1ms"
}

variable "storage_size_gb" {
  description = "Storage size in GB"
  type        = number
  default     = 20
}

variable "storage_iops" {
  description = "IOPS for storage"
  type        = number
  default     = 360
}

variable "backup_retention_days" {
  description = "Backup retention days"
  type        = number
  default     = 7
}

variable "geo_redundant_backup_enabled" {
  description = "Enable geo-redundant backups"
  type        = bool
  default     = false
}

variable "high_availability_enabled" {
  description = "Enable high availability"
  type        = bool
  default     = false
}

variable "zone_redundant_high_availability" {
  description = "Enable zone-redundant high availability"
  type        = bool
  default     = false
}

variable "database_name" {
  description = "Database name"
  type        = string
}

variable "charset" {
  description = "Database charset"
  type        = string
  default     = "utf8"
}

variable "collation" {
  description = "Database collation"
  type        = string
  default     = "utf8_unicode_ci"
}

variable "tags" {
  description = "Resource tags"
  type        = map(string)
  default     = {}
}
