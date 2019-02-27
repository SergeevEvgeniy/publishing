﻿using System.Data.Entity.ModelConfiguration;
using CloudPublishing.Data.Entities;

namespace CloudPublishing.Data.EF.EntityConfigurations
{
    public class PublishingEntityConfiguration : EntityTypeConfiguration<Publishing>
    {
        public PublishingEntityConfiguration()
        {
            ToTable("publishing");
            HasKey(x => x.Id);
            Property(x => x.Id).HasColumnName("id");
            Property(x => x.Title).HasColumnName("title").IsRequired();
            Property(x => x.Type).HasColumnName("type").IsRequired();
            Property(x => x.Subjects).HasColumnName("subjects").IsRequired();

            HasMany<Topic>(x => x.Topics)
                .WithMany(x => x.Publishings)
                .Map( x =>
                    {
                        x.MapLeftKey("publishing_id");
                        x.MapRightKey("topic_id");
                        x.ToTable("publishing_topic");
                    }
                );

        }
    }
}