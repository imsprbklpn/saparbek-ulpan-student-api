#!/bin/bash
# System report for Ulpan Saparbek
PROJECT_DIR="$(cd "$(dirname "$0")/.." && pwd)"
LOG_FILE="$PROJECT_DIR/logs/Saparbek_Ulpan_system_report.log"

{
  echo "=============================="
  echo "System Report"
  echo "=============================="
  echo "Student: Ulpan Saparbek"
  echo "Date and time: $(date '+%Y-%m-%d %H:%M:%S')"
  echo "Username: $(whoami)"
  echo "OS: $(grep PRETTY_NAME /etc/os-release | cut -d= -f2 | tr -d '"')"
  echo "Kernel: $(uname -r)"
  echo "CPU: $(grep -m1 'model name' /proc/cpuinfo | cut -d: -f2 | xargs) ($(nproc) cores)"
  echo "Memory:"
  free -h
  echo "Disk usage:"
  df -h /
  echo "Available disk space: $(df -h / | awk 'NR==2 {print $4}')"
  echo
} | tee -a "$LOG_FILE"
