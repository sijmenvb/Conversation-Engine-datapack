# run as the player

# message id: 1

# reset the sucsess scoreboard
scoreboard players set @s CE_suc 0
scoreboard players set @s CE_resend 0

# check if the player came from a valid previous node (to prevent manual use of /trigger)
execute if score @s CE_current_node matches 0 run scoreboard players set @s CE_suc 1

# special case in case the previous node is itself in that case CE_resend of the player is set to 1 (use this to prevent commands that for example give items are executed twice)
execute store success score @s CE_resend if score @s CE_current_node matches 1 run scoreboard players set @s CE_suc 1

    # give the choices
    execute if score @s CE_suc matches 1 run tellraw @s [{"text":"\n\n\n[lab rat]","color":"#8F7833"},{"text":"hello I am lab rat\n","color":"white"},{"text":"hello","color":"#A8DFFF","clickEvent":{"action":"run_command","value":"/trigger CE_trigger set 2"}}]

    # update the last run node
    execute if score @s CE_suc matches 1 run scoreboard players operation @s CE_current_node = @s CE_trigger

    

