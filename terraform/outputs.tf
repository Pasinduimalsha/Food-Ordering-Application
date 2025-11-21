output "build_server_ip" {
  description = "Public IP address of the build server"
  value       = aws_instance.build_server.public_ip
}