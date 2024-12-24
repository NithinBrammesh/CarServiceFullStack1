token="EAAIM67Dt2i8BOZCJVhJR6GPYjgZBfZB5DcrRQgwmSPNlZBLuT5ZCxYjUT4jEiMsmiBBkpg38VZB69lND1S3T84LE254EzozvSld0TLGRyluLbWnHMwZCWhKKPHOqTVxgwToRu2HjnBbDKZAxoycLeIjwDw55hkUdZAR4xLSJ6cbrbAWdPoXIZAMFUnd1hf7W1iwHBGCPufOfUIEgci2fZAizII2rEyclA9diybug3brnjvVcbnQK6pcCp0ZD"

curl -i -X POST https://graph.facebook.com/v21.0/488929540976682/messages \
-H "Authorization: Bearer ${token}" \
-H "Content-Type: application/json"  \
-d @data.json